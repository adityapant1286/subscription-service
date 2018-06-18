package org.subscription.util;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

public final class AppUtil {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    private AppUtil() {}

    public static String formatDate(Date date) { return dateFormat.format(date); }

    public static String formatDate(LocalDate localDate) { return formatDate(toDate(localDate)); }

    public static LocalDate toLocalDate(Date date) { return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); }

    public static Date toDate(LocalDate localDate) { return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); }

    public static <T> boolean isNull(T...arr) { return Stream.of(arr).anyMatch(Objects::isNull); }

    public static DayOfWeek getDayOfWeek(String occursOn) {

        occursOn = occursOn.toUpperCase();

        if ("MON".equals(occursOn) || "MONDAY".equals(occursOn))
            return DayOfWeek.MONDAY;

        if ("TUE".equals(occursOn) || "TUESDAY".equals(occursOn))
            return DayOfWeek.TUESDAY;

        if ("WED".equals(occursOn) || "WEDNESDAY".equals(occursOn))
            return DayOfWeek.WEDNESDAY;

        if ("THU".equals(occursOn) || "THURSDAY".equals(occursOn))
            return DayOfWeek.THURSDAY;

        if ("FRI".equals(occursOn) || "FRIDAY".equals(occursOn))
            return DayOfWeek.FRIDAY;

        if ("SAT".equals(occursOn) || "SATURDAY".equals(occursOn))
            return DayOfWeek.SATURDAY;

        if ("SUN".equals(occursOn) || "SUNDAY".equals(occursOn))
            return DayOfWeek.SUNDAY;

        return DayOfWeek.TUESDAY;
    }
}
