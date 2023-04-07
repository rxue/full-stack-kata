package com.fa;

import com.fa.data.AccessToken;
import com.fa.service.PortfolioQueryServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.function.Function;

@RestController
public class ReportBuilderController {
    @Autowired
    private PortfolioQueryServiceFacade portfolioQueryService;
    @GetMapping(value = "/portfolio/{id}", produces = "text/csv")
    public Mono<ResponseEntity<byte[]>> getTransactions(@RequestHeader("Authorization") String authorizationHeader,
                                                        @PathVariable long id,
                                                        @RequestParam(name="startDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                        @RequestParam(name="endDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        AuthenticationType authType = AuthenticationType.getAuthenticationType(authorizationHeader);
        if (authType == AuthenticationType.BASIC) {
            final Function<String,String[]> getUsernamePassword = authHeader -> {
                String base64Credentials = authHeader.substring("Basic ".length()).trim();
                byte[] decoded = Base64.getDecoder().decode(base64Credentials);
                String credentials = new String(decoded, StandardCharsets.UTF_8);
                return credentials.split(":", 2);
            };
            String[] usernameThenPassword = getUsernamePassword.apply(authorizationHeader);
            return portfolioQueryService.query(usernameThenPassword[0], usernameThenPassword[1], id, startDate, endDate)
                    .map(bytes -> {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.parseMediaType("text/csv"));
                        headers.setContentDispositionFormData("attachment", "data.csv");
                        return new ResponseEntity<>(bytes, headers, org.springframework.http.HttpStatus.OK);
                    });

        }
        else if (authType == AuthenticationType.BEARER){
            AccessToken token = new AccessToken();
            token.setTokenType("Bearer");
            token.setAccessToken(authorizationHeader.substring("Basic ".length()).trim());
            return portfolioQueryService.query(token, id, startDate, endDate)
                    .map(bytes -> {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.parseMediaType("text/csv"));
                        headers.setContentDispositionFormData("attachment", "data.csv");
                        return new ResponseEntity<>(bytes, headers, org.springframework.http.HttpStatus.OK);
                    });
        } else {
            return Mono.just(new ResponseEntity<>(new byte[0], new HttpHeaders(), HttpStatus.UNAUTHORIZED));
        }
    }
    private enum AuthenticationType {
        BASIC, BEARER, OTHER;
        static AuthenticationType getAuthenticationType(String authorizationHeader) {
            if (authorizationHeader != null) {
                if (authorizationHeader.startsWith("Basic "))
                    return BASIC;
                else if (authorizationHeader.startsWith("Bearer "))
                    return BEARER;
                return OTHER;
            }
            return null;
        }
    }
}
