package com.fa.service;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
abstract class AbstractIntegrationTest {
    protected static String username;
    protected static String password;
    @BeforeAll
    public static void init() {
        username = getProperty("username");
        password = getProperty("password");
    }
    private static String getProperty(String property) {
        return System.getenv(property) == null ? System.getProperty(property) : System.getenv(property);
    }
}
