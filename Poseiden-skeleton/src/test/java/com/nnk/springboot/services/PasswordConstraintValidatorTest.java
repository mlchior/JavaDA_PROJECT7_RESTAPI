package com.nnk.springboot.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PasswordConstraintValidatorTest {

    private PasswordConstraintValidator passwordConstraintValidator;
    private ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    public void setUp() {
        passwordConstraintValidator = new PasswordConstraintValidator();
        constraintValidatorContext = mock(ConstraintValidatorContext.class);
    }

    @Test
    public void whenPasswordIsShort_thenInvalid() {
        assertFalse(passwordConstraintValidator.isValid("Aa1", constraintValidatorContext));
    }

    @Test
    public void whenPasswordHasNoUppercase_thenInvalid() {
        assertFalse(passwordConstraintValidator.isValid("abcd1234", constraintValidatorContext));
    }

    @Test
    public void whenPasswordIsCorrect_thenValid() {
        assertTrue(passwordConstraintValidator.isValid("Abcd1234", constraintValidatorContext));
    }
}
