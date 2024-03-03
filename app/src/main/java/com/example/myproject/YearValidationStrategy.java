package com.example.myproject;
import java.util.Calendar;

public class YearValidationStrategy implements ValidationStrategy{

    @Override
    public boolean isValid(String year) {
        if (year == null || year.isEmpty() || !year.matches("\\d{4}")) {
            return false;
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int inputYear = Integer.parseInt(year);

        return inputYear >= currentYear;
    }
}
