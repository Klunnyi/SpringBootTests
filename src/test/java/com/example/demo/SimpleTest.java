package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {

    @Test
    public void test() {
        int a = 5;
        int b = 5;

        //результат который мы хотим получить / актуальный результат a + b
        Assert.assertEquals(10, a + b);
        Assert.assertEquals(25, a * b);
    }

    @Test(expected = ArithmeticException.class)
    public void error() {
        int i = 0;
        int i1 = 1 / i;
    }
}
