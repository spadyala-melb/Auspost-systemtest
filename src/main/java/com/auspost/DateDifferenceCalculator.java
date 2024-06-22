package com.auspost;

import static com.auspost.util.DateUtil.calculateDateDifference;
import static com.auspost.util.DateUtil.parseDate;

/**
 * Calculates the difference between 2 dates and returns number of days.
 */
public class DateDifferenceCalculator {

    public int calculate(String dateFrom, String dateTo) {

        int[] parsedDate1 = parseDate(dateFrom);
        int[] parsedDate2 = parseDate(dateTo);

        if (parsedDate1 == null || parsedDate2 == null) {
            System.out.println("Invalid date format. Please enter dates in 'DD MM YYYY, DD MM YYYY' format.");
            return 0;
        }

        return calculateDateDifference(parsedDate1, parsedDate2);
    }

}
