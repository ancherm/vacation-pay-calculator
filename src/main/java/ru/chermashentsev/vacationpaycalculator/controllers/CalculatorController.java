package ru.chermashentsev.vacationpaycalculator.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.chermashentsev.vacationpaycalculator.services.CalculatorService;

import java.time.LocalDate;

@RestController
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculate")
    public double calculatePayVacation(@RequestParam double salaryForYear,
                                       @RequestParam int countDays,
                                       @RequestParam(required = false) String startVacationDay) {

        LocalDate startVacationLocalDate = LocalDate.parse(startVacationDay);
        return calculatorService.calculatePayVacation(salaryForYear, countDays, startVacationLocalDate);
    }
}
