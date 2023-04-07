package com.fa.service;

class TestUtil {
    private TestUtil() {}
    static String getProperty(String property) {
        return System.getenv(property) == null ? System.getProperty(property) : System.getenv(property);
    }
}
