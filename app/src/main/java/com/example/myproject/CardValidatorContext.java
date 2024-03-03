package com.example.myproject;

public class CardValidatorContext {
    private ValidationStrategy validationStrategy;

    public void setValidationStrategy(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String input) {
        return validationStrategy.isValid(input);
    }
}
