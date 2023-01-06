package com.example.mathapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EquationRequestDto {
    private String input;
    private Double result;
}
