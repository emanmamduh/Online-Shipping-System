package com.example.myproject;

public class CardValidationStrategy implements ValidationStrategy{
    @Override
    public boolean isValid(String card) {
        if (card == null || card.isEmpty() || !card.matches("\\d+")) {
            return false; // Invalid if null, empty, or contains non-digit characters
        }

        // Apply the Luhn algorithm
        int sum = 0;
        boolean alternate = false;

        for (int i = card.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(card.charAt(i));

            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }

            sum += digit;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }
}
