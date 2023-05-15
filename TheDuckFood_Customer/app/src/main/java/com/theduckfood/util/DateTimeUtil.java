package com.theduckfood.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static Date convertStringToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       return format.parse(date);
    }

    public static Date getDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return  calendar.getTime();
    }
}
