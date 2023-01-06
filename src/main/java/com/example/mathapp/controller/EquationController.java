package com.example.mathapp.controller;

import com.example.mathapp.dto.EquationResponseDto;
import com.example.mathapp.dto.mapper.EquationMapper;
import com.example.mathapp.model.Equation;
import com.example.mathapp.repository.EquationRepository;
import com.example.mathapp.service.EquationService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equations")
public class EquationController {
    private final EquationRepository equationRepository;
    private final EquationMapper equationMapper;
    private final EquationService equationService;

    public EquationController(EquationRepository equationRepository, EquationMapper equationMapper,
            EquationService equationService) {
        this.equationRepository = equationRepository;
        this.equationMapper = equationMapper;
        this.equationService = equationService;
    }

    @PostMapping(value = "/create")
    public EquationResponseDto create(@RequestParam String input) {
        Equation equation = equationService.create(input);
        equationRepository.save(equation);
        return equationMapper.toResponseDto(equation);
    }

    @GetMapping("/{id}")
    public EquationResponseDto get(@PathVariable Long id) {
        return equationMapper.toResponseDto(equationService.get(id));
    }

    @PutMapping("/{id}")
    public EquationResponseDto update(@PathVariable Long id,
            @RequestParam String input) {
        Equation equation = equationService.create(input);
        equation.setId(id);
        equationRepository.save(equation);
        return equationMapper.toResponseDto(equationService.get(id));
    }

    @GetMapping
    public List<EquationResponseDto> getAll() {
        return equationService.findAll()
                .stream()
                .map(equationMapper::toResponseDto)
                .toList();
    }

    @GetMapping("/by-result")
    public List<EquationResponseDto> findAllByResultBetween(@RequestParam Double from,
            @RequestParam Double to) {
        List<Equation> equationList = equationService.findAllByResultBetween(from, to);
        return equationList.stream()
                .map(equationMapper::toResponseDto)
                .toList();
    }

    @GetMapping("/by-result-after")
    public List<EquationResponseDto> findAllByResultAfter(@RequestParam Double after) {
        List<Equation> equationList = equationService.findAllByResultAfter(after);
        return equationList.stream()
                .map(equationMapper::toResponseDto)
                .toList();
    }

    @GetMapping("/by-result-before")
    public List<EquationResponseDto> findAllByResultBefore(@RequestParam Double before) {
        List<Equation> equationList = equationService.findAllByResultBefore(before);
        return equationList.stream()
                .map(equationMapper::toResponseDto)
                .toList();
    }

    @GetMapping("/validate-brackets")
    public boolean validate(@RequestParam String input) {
        return equationService.validate(input);
    }
}
