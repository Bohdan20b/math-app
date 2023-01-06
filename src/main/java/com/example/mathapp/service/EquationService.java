package com.example.mathapp.service;

import com.example.mathapp.model.Equation;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface EquationService {
    Equation create(String input);

    Equation get(Long id);

    boolean validate(String input);

    List<Equation> findAll();

    List<Equation> findAllByResultBetween(Double from, Double to);

    List<Equation> findAllByResultAfter(Double after);

    List<Equation> findAllByResultBefore(Double before);
}
