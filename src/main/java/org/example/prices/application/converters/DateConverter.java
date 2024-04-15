package org.example.prices.application.converters;

import java.time.LocalDateTime;

public interface DateConverter {

    LocalDateTime convert(String date, String pattern);
    String convert(LocalDateTime localDateTime, String pattern);
}
