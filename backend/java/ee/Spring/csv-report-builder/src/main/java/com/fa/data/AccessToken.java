package com.fa.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@JsonIgnoreProperties({"expires_in", "refresh_expires_in","refresh_token", "not-before-policy", "session_state", "scope"})
@Data
public class AccessToken {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @Override
    public String toString() {
        return tokenType + " " + accessToken;
    }
    public boolean isEmpty() {
        return accessToken == null && tokenType == null;
    }
}
