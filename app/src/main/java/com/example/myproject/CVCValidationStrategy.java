package com.example.myproject;

public class CVCValidationStrategy implements ValidationStrategy{
    @Override
    public boolean isValid(String cvc) {
        if (cvc == null || cvc.isEmpty() || !cvc.matches("\\d{3}")) {
            return false;
        }

        return true;
    }
}
