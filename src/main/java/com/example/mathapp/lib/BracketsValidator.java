package com.example.mathapp.lib;

import java.util.Stack;

public class BracketsValidator {
    public static boolean validate(String text) {
        if (text.contains("()")) {
            return false;
        }
        char[] brackets = text.replaceAll("[^()]+","").toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : brackets) {
            if (c == '(')
                stack.push(')');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}

