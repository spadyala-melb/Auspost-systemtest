package com.auspost.util;

/**
 * Utility class for Dates calculator
 */
public class DateUtil {

    public static int[] parseDate(String date) {
        String[] dateParts = date.split(" ");
        if (dateParts.length != 3) {
            return null;
        }

        int day;
        int month;
        int year;

        try {
            day = Integer.parseInt(dateParts[0]);
            month = Integer.parseInt(dateParts[1]);
            year = Integer.parseInt(dateParts[2]);
        } catch (NumberFormatException e) {
            return null;
        }

        if (!isValidDate(day, month, year)) {
            return null;
        }

        return new int[]{day, month, year};
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1900 || year > 2020) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }
        return day > 0 && day <= daysInMonth[month - 1];
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        }
        return false;
    }

    public static int checkDatesOrder(int[] date1, int[] date2) {
        for (int i = 2; i >= 0; i--) {
            if (date1[i] != date2[i]) {
                return date1[i] - date2[i];
            }
        }
        return 0;
    }

    public static int calculateDateDifference(int[] date1, int[] date2) {
        if (checkDatesOrder(date1, date2) > 0) {
            int[] temp = date1;
            date1 = date2;
            date2 = temp;
        }
        int days1 = calculateDaysSinceEpoch(date1[0], date1[1], date1[2]);
        int days2 = calculateDaysSinceEpoch(date2[0], date2[1], date2[2]);
        return days2 - days1;
    }

    public static int calculateDaysSinceEpoch(int day, int month, int year) {
        int days = 0;
        for (int y = 1900; y < year; y++) {
            days += isLeapYear(y) ? 366 : 365;
        }
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }
        for (int m = 1; m < month; m++) {
            days += daysInMonth[m - 1];
        }
        days += day - 1;
        return days;
    }

}
