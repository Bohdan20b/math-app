package com.example.mathapp.controller;

import com.example.mathapp.dto.EquationRequestDto;
import com.example.mathapp.model.Equation;
import com.example.mathapp.service.EquationService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.List;
import java.util.function.BooleanSupplier;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EquationControllerTest {

    @MockBean
    private EquationService equationService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldShowAllEquations() {
        List<Equation> mockEquations = List.of(
                new Equation(1L,"(1+1)*(2+2)",8.0),
                new Equation(2L,"2.5/5",0.5),
                new Equation(3L,"(4-1)/(1+2)",1.0)
        );
        Mockito.when(equationService.findAll()).thenReturn(mockEquations);
        RestAssuredMockMvc.when()
                .get("/equations")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].input", Matchers.equalTo("(1+1)*(2+2)"))
                .body("[0].result", Matchers.equalTo(8.0f))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].input", Matchers.equalTo("2.5/5"))
                .body("[1].result", Matchers.equalTo(0.5f))
                .body("[2].id", Matchers.equalTo(3))
                .body("[2].input", Matchers.equalTo("(4-1)/(1+2)"))
                .body("[2].result", Matchers.equalTo(1.0f));
    }

    @Test
    public void shouldReturnAllEquationsWhereResultBetweenTwoValues() {
        Double from = 0.1;
        Double to = 0.9;
        List<Equation> mockEquations = List.of(
                new Equation(5L,"2.5/5",0.5));
        Mockito.when(equationService.findAllByResultBetween(from,to))
                .thenReturn(mockEquations);

        RestAssuredMockMvc.
                given()
                .queryParam("from", from)
                .queryParam("to", to)
                .when()
                .get("/equations/by-result")
                .then()
                .body("[0].id", Matchers.equalTo(5))
                .body("[0].input", Matchers.equalTo("2.5/5"))
                .body("[0].result", Matchers.equalTo(0.5f));
    }
    @Test
    public void shouldReturnAllEquationsWhereResultIsMoreThenValue() {
        Double after = 0.1;
        List<Equation> mockEquations = List.of(
                new Equation(9L,"2.5/5",0.5),
                new Equation(10L,"(4-1)/(1+2)",1.0)
                );
        Mockito.when(equationService.findAllByResultAfter(after))
                .thenReturn(mockEquations);

        RestAssuredMockMvc.
                given()
                .queryParam("after", after)
                .when()
                .get("/equations/by-result-after")
                .then()
                .body("[0].id", Matchers.equalTo(9))
                .body("[0].input", Matchers.equalTo("2.5/5"))
                .body("[0].result", Matchers.equalTo(0.5f))
                .body("[1].id", Matchers.equalTo(10))
                .body("[1].input", Matchers.equalTo("(4-1)/(1+2)"))
                .body("[1].result", Matchers.equalTo(1.0f));

    }

    @Test
    public void shouldReturnAllEquationsWhereResultIsLessThenValue() {
        Double after = 0.1;
        List<Equation> mockEquations = List.of(
                new Equation(9L,"2.5/5",0.5),
                new Equation(10L,"(4-1)/(1+2)",1.0)
        );
        Mockito.when(equationService.findAllByResultAfter(after))
                .thenReturn(mockEquations);

        RestAssuredMockMvc.
                given()
                .queryParam("after", after)
                .when()
                .get("/equations/by-result-after")
                .then()
                .body("[0].id", Matchers.equalTo(9))
                .body("[0].input", Matchers.equalTo("2.5/5"))
                .body("[0].result", Matchers.equalTo(0.5f))
                .body("[1].id", Matchers.equalTo(10))
                .body("[1].input", Matchers.equalTo("(4-1)/(1+2)"))
                .body("[1].result", Matchers.equalTo(1.0f));

    }

    @Test
    public void shouldCreateEquation() {
        Equation equationToSave = new Equation(55L,"(4-1)/(1+2)",1.0);
        Mockito.when(equationService.create(equationToSave.getInput()))
                .thenReturn(new Equation(55L,"(4-1)/(1+2)",1.0));
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .param("input","(4-1)/(1+2)")
                .when()
                .post("/equations/create")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(55))
                .body("input",Matchers.equalTo("(4-1)/(1+2)"))
                .body("result", Matchers.equalTo(1.0f));
    }
}
