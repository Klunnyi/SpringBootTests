package com.example.demo.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordEncoderTest {

    @Test
    void encode() {
        PasswordEncoder encoder = new PasswordEncoder();

        assertEquals("secret: mypwd", encoder.encode("mypwd"));
        MatcherAssert.assertThat(encoder.encode("mypwd"), Matchers.containsString("mypwd"));
    }
}