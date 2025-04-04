package io.github.rxue.account;

import io.github.rxue.account.entity.Event;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(path="/api/account")
public class GameAccountController {
    private GameAccountService gameAccountService;
    public GameAccountController(GameAccountService gameAccountService) {
        this.gameAccountService = gameAccountService;
    }
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "win or charge succeeded"),
            @ApiResponse(responseCode = "422", description = "charge failed due to insufficient balance")
    })
    @PutMapping("event")
    public BigDecimal action(@RequestBody Event event) {
        return gameAccountService.action(event);
    }
}

