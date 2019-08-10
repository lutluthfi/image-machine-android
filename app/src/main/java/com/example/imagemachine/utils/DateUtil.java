package com.example.imagemachine.utils;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

    private static Calendar calendar = Calendar.getInstance();
    private static String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    private static String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
    private static String year = Integer.toString(calendar.get(Calendar.YEAR));

    public static String dateYmd() {
        if (month.length() == 1) {
            return year + "-" + "0" + month + "-" + day;
        } else {
            return year + "-" + month + "-" + day;
        }
    }

    public static String formatToDmy(@NonNull Date date) {
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date);
    }
}
