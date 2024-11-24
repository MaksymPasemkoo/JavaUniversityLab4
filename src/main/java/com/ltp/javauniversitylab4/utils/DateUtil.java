package com.ltp.javauniversitylab4.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate dateFormatter(final String date){
        try {
            return LocalDate.parse(date, dateTimeFormatter);
        }catch (DateTimeParseException e){
            throw new BookingException("Format must be 'dd-MM-yyyy'");
        }
    }

}
