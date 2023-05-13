package com.theduckfood.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {
    public static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
