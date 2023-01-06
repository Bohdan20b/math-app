package com.example.mathapp.lib;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BracketsValidatorTest {

    @Test
    void validExpressionsShouldValidate() {
        int expected = 10;
        int actual = 0;
        List<String> validInputList = new ArrayList<>();
        validInputList.add("(1+1)*(2+2)");
        validInputList.add("2.5/5");
        validInputList.add("(4-1)/(1+2)");
        validInputList.add("7.7-10");
        validInputList.add("50/2*(1+1)");
        validInputList.add("10/2*0");
        validInputList.add("5.4*(1.1-0.1)");
        validInputList.add("3.6/0.6*10");
        validInputList.add("5.5-3*(2+3)+20");
        validInputList.add("(0+1)-(0/1)");
        for (String s : validInputList) {
            if (BracketsValidator.validate(s)) {
                actual++;
            }
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void invalidExpressionShouldNotValidate() {
        int expected = 0;
        int actual = 0;
        List<String> invalidInputList = new ArrayList<>();
        invalidInputList.add("(1+1)*)2+2)");
        invalidInputList.add("(2.5/5");
        invalidInputList.add("(4-1))");
        invalidInputList.add("7.7-10))");
        invalidInputList.add("(50/2*(1+1)");
        invalidInputList.add("(10/2(*0");
        invalidInputList.add("(5.4*(1.1-0.1)");
        invalidInputList.add("3.6)(/0.6*10");
        invalidInputList.add("()5.5-3*(2+3)+20");
        invalidInputList.add(")(0+1)-(0/1)");
        for (String s : invalidInputList) {
            if (BracketsValidator.validate(s)) {
                actual++;
            }
        }
        Assertions.assertEquals(expected, actual);
    }
}
