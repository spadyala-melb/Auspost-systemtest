package com.auspost;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateDifferenceCalculatorTest {

    private DateDifferenceCalculator dateDifferenceCalculator;

    @Before
    public void initialize() {
        dateDifferenceCalculator = new DateDifferenceCalculator();
    }

    // Test invalid date format
    @Test
    public void testCalculateInvalidDateFormat() {
        assertEquals(0, dateDifferenceCalculator.calculate("1/1/1990", "1 1 1980"));
    }

    // Test invalid day
    @Test
    public void testCalculateInvalidDay() {
        assertEquals(0, dateDifferenceCalculator.calculate("32 1 1990", "1 1 1980"));
    }

    // Test invalid month
    @Test
    public void testCalculateInvalidMonth() {
        assertEquals(0, dateDifferenceCalculator.calculate("1 13 1990", "1 1 1980"));
    }

    // Test non-numeric input
    @Test
    public void testCalculateNonNumericInput() {
        assertEquals(0, dateDifferenceCalculator.calculate("1 Jan 1990", "1 1 1980"));
    }

    // Test empty string input
    @Test
    public void testCalculateEmptyStringInput() {
        assertEquals(0, dateDifferenceCalculator.calculate("", "1 1 1980"));
    }

    // Test start date after end date
    @Test
    public void testCalculateStartDateAfterEndDate() {
        int expectedDifference = 3653;
        int actualDifference = dateDifferenceCalculator.calculate("1 1 1980", "1 1 1990");

        assertEquals(expectedDifference, actualDifference);
    }

    // same dates
    @Test
    public void testCalculateSameDates() {
        int expectedDifference = 0;
        int actualDifference = dateDifferenceCalculator.calculate("1 1 1990", "1 1 1990");

        assertEquals(expectedDifference, actualDifference);
    }

    // Test  for leap year
    @Test
    public void testCalculateLeapYearDifference() {
        int expectedDifference = 1462;
        int actualDifference = dateDifferenceCalculator.calculate("29 2 2016", "28 2 2012");

        assertEquals(expectedDifference, actualDifference);
    }

}
