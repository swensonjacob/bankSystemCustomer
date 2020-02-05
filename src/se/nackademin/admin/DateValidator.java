package se.nackademin.admin;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateValidator {
    static public boolean isValid (String date) {
        try {
            LocalDate.parse(date);

        } catch (DateTimeParseException e) {
            return false;
        }
        return !LocalDate.parse(date).isAfter(LocalDate.now());
    }
    static public boolean isStartDateBeforeEndDate(String startDate, String endDate) {
        return LocalDate.parse(startDate).isBefore(LocalDate.parse(endDate));
    }
}
