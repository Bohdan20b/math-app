package com.example.mathapp.dto.mapper;

import com.example.mathapp.dto.EquationRequestDto;
import com.example.mathapp.dto.EquationResponseDto;
import com.example.mathapp.model.Equation;
import com.example.mathapp.service.EquationService;
import org.springframework.stereotype.Component;

@Component
public class EquationMapper {
    private final EquationService equationService;

    public EquationMapper(EquationService equationService) {
        this.equationService = equationService;
    }

    public EquationResponseDto toResponseDto(Equation equation) {
        EquationResponseDto responseDto = new EquationResponseDto();
        responseDto.setId(equation.getId());
        responseDto.setInput(equation.getInput());
        responseDto.setResult(equation.getResult());
        return responseDto;
    }

    public Equation toModel(EquationRequestDto requestDto) {
        Equation equation = new Equation();
        equation.setInput(requestDto.getInput());
        equation.setResult(requestDto.getResult());
        return equation;
    }
}
