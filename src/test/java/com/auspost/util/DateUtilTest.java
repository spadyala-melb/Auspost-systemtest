package com.auspost.util;

import org.junit.Test;

import static com.auspost.util.DateUtil.calculateDateDifference;
import static com.auspost.util.DateUtil.calculateDaysSinceEpoch;
import static com.auspost.util.DateUtil.checkDatesOrder;
import static com.auspost.util.DateUtil.isLeapYear;
import static com.auspost.util.DateUtil.isValidDate;
import static com.auspost.util.DateUtil.parseDate;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DateUtilTest {

    @Test
    public void testValidDate() {
        int[] expected = {15, 6, 1981};
        int[] result = parseDate("15 6 1981");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testOutOfRangeDate() {
        assertNull(parseDate("15 6 1889"));
        assertNull(parseDate("15 6 2024"));
    }

    @Test
    public void testInvalidDateFormat() {
        assertNull(parseDate("15-6-2024"));
        assertNull(parseDate("15 six 2024")); // Non-numeric month
        assertNull(parseDate("15 6 two thousand twenty-four")); // Non-numeric year
        assertNull(parseDate("30 2 2024")); // February 30th
        assertNull(parseDate("31 9 2024")); // Sep 31st
    }

    @Test
    public void testIsValidDate() {
        assertTrue(isValidDate(1, 1, 2000));
        assertTrue(isValidDate(29, 2, 2000));
        assertFalse(isValidDate(32, 1, 2000));
        assertFalse(isValidDate(29, 2, 2001));
    }

    @Test
    public void testIsLeapYear() {
        assertTrue(isLeapYear(2000));
        assertFalse(isLeapYear(1900));
        assertTrue(isLeapYear(2004));
        assertFalse(isLeapYear(2001));
    }

    @Test
    public void testCheckDatesOrder() {
        assertEquals(0, checkDatesOrder(new int[]{1, 1, 2000}, new int[]{1, 1, 2000}));
        assertTrue(checkDatesOrder(new int[]{1, 1, 1999}, new int[]{1, 1, 2000}) < 0);
        assertTrue(checkDatesOrder(new int[]{2, 1, 2000}, new int[]{1, 1, 2000}) > 0);
    }

    @Test
    public void testCalculateDaysSinceEpoch() {
        assertEquals(0, calculateDaysSinceEpoch(1, 1, 1900)); // January 1, 1900
        assertEquals(32872, calculateDaysSinceEpoch(1, 1, 1990));
        assertEquals(35063, calculateDaysSinceEpoch(1, 1, 1996)); // leap year
    }

    @Test
    public void testCalculateDateDifference() {
        // Test with dates where date2 is later than date1
        int[] date1 = {1, 1, 1990};
        int[] date2 = {1, 1, 1980};
        assertEquals(3653, calculateDateDifference(date1, date2));

        // Test with dates where date1 is later than date2
        int[] date3 = {1, 1, 1990};
        int[] date4 = {1, 1, 1980};
        assertEquals(3653, calculateDateDifference(date3, date4)); // Leap year difference (reversed)

        // Test with identical dates
        int[] date5 = {1, 1, 2000};
        assertEquals(0, calculateDateDifference(date5, date5)); // Same date

    }

}
