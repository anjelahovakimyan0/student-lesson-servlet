package org.example.studentlessonservlet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm");

    public static String dateToString(Date date) {
        return SDF.format(date);
    }

    public static Date stringToDate(String daTeStr) {
        try {
            return SDF.parse(daTeStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
