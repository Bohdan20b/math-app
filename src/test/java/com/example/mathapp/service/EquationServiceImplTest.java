package com.example.mathapp.service;

import com.example.mathapp.repository.EquationRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EquationServiceImplTest {
    @InjectMocks
    private EquationServiceImpl equationService;


    @Test
    void shouldReturnCreatedEquation() {
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

        List<Double> expectedResults = new ArrayList<>();
        expectedResults.add(8d);
        expectedResults.add(0.5);
        expectedResults.add(1d);
        expectedResults.add(-2.3);
        expectedResults.add(50d);
        expectedResults.add(0d);
        expectedResults.add(5.4);
        expectedResults.add(60d);
        expectedResults.add(10.5);
        expectedResults.add(1d);

        List<Double> actualResults = new ArrayList<>();
        for (int i = 0; i < validInputList.size(); i++) {
            actualResults.add(equationService.create(validInputList.get(i)).getResult());
        }
        Assertions.assertEquals(expectedResults,actualResults);
    }

}
