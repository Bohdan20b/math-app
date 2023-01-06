package com.example.mathapp.repository;

import com.example.mathapp.model.Equation;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquationRepository extends JpaRepository<Equation, Long> {
    List<Equation> findAllByResultBetween(Double from, Double to);

    List<Equation> findAllByResultAfter(Double after);
    List<Equation> findAllByResultBefore(Double before);

}
