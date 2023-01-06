package com.example.mathapp.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquationResponseDto {
    private Long id;
    private String input;
    private Double result;
}
