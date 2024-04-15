package org.example.prices.application.converters.impl;

import org.apache.commons.lang3.StringUtils;
import org.example.prices.application.converters.DateConverter;
import org.example.prices.application.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Component
public final class DateConverterImpl implements DateConverter{

    @Override
    public LocalDateTime convert(String date, String pattern) {
        if (StringUtils.isEmpty(date) || StringUtils.isBlank(date)
                || StringUtils.isEmpty(pattern) || StringUtils.isBlank(pattern)) {
            throw new ValidationException("Date and pattern cannot be null or empty");
        }

        LocalDateTime convertedDate;
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            convertedDate = LocalDateTime.parse(date, formatter);
        }catch (DateTimeParseException e){
            throw new ValidationException("Invalid date format");
        }catch (IllegalArgumentException e){
            throw new ValidationException(String.format("Incorrect pattern: %s", pattern));
        }

        return convertedDate;
    }

    @Override
    public String convert(LocalDateTime date, String pattern) {
        if (Objects.isNull(date) || StringUtils.isEmpty(pattern) || StringUtils.isBlank(pattern)) {
            throw new ValidationException("Date and pattern cannot be null or empty");
        }

        String convertedDate;
        try{
            convertedDate = date.format(DateTimeFormatter.ofPattern(pattern));
        }catch (DateTimeException e){
            throw new ValidationException("Invalid date format");
        }catch (IllegalArgumentException e){
            throw new ValidationException(String.format("Incorrect pattern: %s", pattern));
        }

        return convertedDate;
    }
}
