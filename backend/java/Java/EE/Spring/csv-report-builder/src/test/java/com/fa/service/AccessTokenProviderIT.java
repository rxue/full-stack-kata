package com.fa.service;

import com.fa.data.AccessToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccessTokenProviderIT extends AbstractIntegrationTest {
    @Test
    public void getAccessToken_succeeded() {
        AccessToken accessToken = new AccessTokenProvider()
                .getToken(username, password)
                .block();
        assertEquals("Bearer", accessToken.getTokenType());
        assertTrue(accessToken.getAccessToken().length() > 0);
    }

    @Test
    public void getAccessToken_wrongPassword() {
        AccessToken accessToken = new AccessTokenProvider()
                .getToken(username, "xxx")
                .block();
        assertTrue(accessToken.isEmpty());
    }
}
