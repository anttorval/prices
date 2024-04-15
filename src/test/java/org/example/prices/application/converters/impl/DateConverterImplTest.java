package org.example.prices.application.converters.impl;

import org.example.prices.application.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateConverterImplTest {

    @InjectMocks
    private DateConverterImpl dateConverterImpl;

    private String validStringDate;
    private String validPattern;
    private LocalDateTime validDate;

    @BeforeEach
    void init() {
        validStringDate = this.getValidStringDate();
        validPattern = this.getValidPattern();
        validDate = this.getValidDate();
    }

    private LocalDateTime getValidDate() {
        return LocalDateTime.of(2024, 04, 12, 22, 12, 05);
    }

    private String getValidPattern() {
        return "dd/MM/yyyy HH:mm:ss";
    }

    private String getValidStringDate() {
        return "12/04/2024 22:12:05";
    }

    @Test
    void givenValidStringDateAndPattern_whenConvert_thenSucceed() {
        // When
        LocalDateTime result = dateConverterImpl.convert(validStringDate, validPattern);

        LocalDateTime expectedDate = LocalDateTime.of(2024, 04, 12, 22, 12, 05);

        // Then
        assertNotNull(result);

        assertEquals(expectedDate, result);
    }

    @Test
    void givenValidLocalDateTimeAndPattern_whenConvert_thenSucceed() {
        // When
        String result = dateConverterImpl.convert(validDate, validPattern);

        // Then
        assertNotNull(result);

        assertEquals(validStringDate, result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void givenNullOrEmptyOrBlankStringDate_whenConvert_thenValidationException(String date) {
        String pattern = "dd/MM/yyyy HH:mm:ss";

        ValidationException exception =
                assertThrows(ValidationException.class, () -> dateConverterImpl.convert(date, validPattern));

        assertEquals("Date and pattern cannot be null or empty", exception.getMessage());
    }

    @Test
    void givenInvalidStringDate_whenConvert_thenValidationException() {
        String pattern = "dd/MM/yyyy HH:mm:ss";

        ValidationException exception =
                assertThrows(ValidationException.class, () -> dateConverterImpl.convert("invalid date", validPattern));

        assertEquals("Invalid date format", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void givenNullOrEmptyOrBlankPattern_whenConvert_thenValidationException(String pattern) {
        ValidationException exception =
                assertThrows(ValidationException.class, () -> dateConverterImpl.convert(validStringDate, pattern));

        assertEquals("Date and pattern cannot be null or empty", exception.getMessage());
    }

    @Test
    void givenInvalidPattern_whenConvert_thenValidationException() {
        ValidationException exception =
                assertThrows(ValidationException.class, () -> dateConverterImpl.convert(validStringDate, "test invalid pattern"));

        assertEquals("Incorrect pattern: test invalid pattern", exception.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void givenNullLocalDateTime_whenConvert_thenValidationException(LocalDateTime date) {
        ValidationException exception =
                assertThrows(ValidationException.class, () -> dateConverterImpl.convert(date, validPattern));

        assertEquals("Date and pattern cannot be null or empty", exception.getMessage());
    }


}
