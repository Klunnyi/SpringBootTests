package com.example.demo.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {

    @Test
    void encode() {
        PasswordEncoder encoder = new PasswordEncoder();

        assertEquals("secret: mypwd", encoder.encode("mypwd"));
        MatcherAssert.assertThat(encoder.encode("mypwd"), Matchers.containsString("mypwd"));
    }
}