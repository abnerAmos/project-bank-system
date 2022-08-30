package com.banksystem.banksystem.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidandoData {

    public static LocalDate stringToLocalDate(String value, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(value, formatter);
    }

    public static Date dataLocal(LocalDate dataLocal) {
        return Date.valueOf(dataLocal);
    }
}
