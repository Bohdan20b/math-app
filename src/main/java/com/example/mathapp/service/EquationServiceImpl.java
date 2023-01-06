package com.example.mathapp.service;

import com.example.mathapp.lib.BracketsValidator;
import com.example.mathapp.model.Equation;
import com.example.mathapp.repository.EquationRepository;
import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EquationServiceImpl implements EquationService {
    private final EquationRepository equationRepository;

    public EquationServiceImpl(EquationRepository equationRepository) {
        this.equationRepository = equationRepository;
    }

    @Override
    public Equation create(String input) {
        try {
            Equation equation = new Equation();
            Expression expression = new Expression(input);
            EvaluationValue result = expression.evaluate();
            equation.setInput(input);
            equation.setResult(result.getNumberValue().doubleValue());
            return equation;
        } catch (EvaluationException | ParseException e) {
            throw new RuntimeException("Can't create an expression.", e);
        }
    }

    @Override
    public Equation get(Long id) {
        return equationRepository.getReferenceById(id);
    }

    @Override
    public boolean validate(String input) {
        return BracketsValidator.validate(input);
    }

    @Override
    public List<Equation> findAll() {
        return equationRepository.findAll();
    }

    @Override
    public List<Equation> findAllByResultBetween(Double from, Double to) {
        return equationRepository.findAllByResultBetween(from, to);
    }

    @Override
    public List<Equation> findAllByResultAfter(Double after) {
        return equationRepository.findAllByResultAfter(after);
    }

    @Override
    public List<Equation> findAllByResultBefore(Double before) {
        return equationRepository.findAllByResultBefore(before);
    }
}
