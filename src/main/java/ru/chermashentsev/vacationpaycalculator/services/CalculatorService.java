package ru.chermashentsev.vacationpaycalculator.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CalculatorService {

    public double calculatePayVacation(double salaryForYear, int countDays, String startVacationDay) {
        double dailySalary = salaryForYear / 365;

        if (startVacationDay == null) {
            return dailySalary * countDays;
        }

        LocalDate startVacationLocalDate = LocalDate.parse(startVacationDay);

        List<LocalDate> vacationDays = IntStream.range(0, countDays)
                .mapToObj(startVacationLocalDate::plusDays)
                .collect(Collectors.toList());

        //Delete
        System.out.println(vacationDays);

        long workDays = vacationDays.stream()
                .filter(date -> !isWeekend(date) && !isHoliday(date))
                .count();


        return dailySalary * workDays;
    }

    private boolean isHoliday(LocalDate date) {
        List<LocalDate> holidays = List.of(
                LocalDate.of(date.getYear(), 1, 1),
                LocalDate.of(date.getYear(), 1, 2),
                LocalDate.of(date.getYear(), 1, 3),
                LocalDate.of(date.getYear(), 1, 4),
                LocalDate.of(date.getYear(), 1, 5),
                LocalDate.of(date.getYear(), 1, 6),
                LocalDate.of(date.getYear(), 1, 7),
                LocalDate.of(date.getYear(), 1, 8),

                LocalDate.of(date.getYear(), 2, 23),
                LocalDate.of(date.getYear(), 3, 8),
                LocalDate.of(date.getYear(), 5, 1),
                LocalDate.of(date.getYear(), 5, 9),
                LocalDate.of(date.getYear(), 6, 12),
                LocalDate.of(date.getYear(), 11, 4)
        );

        return holidays.contains(date);
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

}
