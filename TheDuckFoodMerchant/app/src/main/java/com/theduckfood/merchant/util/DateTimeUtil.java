package com.theduckfood.merchant.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
    public static String formatTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static String formatShortDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
        return formatter.format(date);
    }

    public static String formatCurrency(String currency) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
        return formatter.format(Float.parseFloat(currency));
    }
}
