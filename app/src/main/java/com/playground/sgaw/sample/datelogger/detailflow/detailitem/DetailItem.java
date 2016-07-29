package com.playground.sgaw.sample.datelogger.detailflow.detailitem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Model class to represent the logged date.
 */
public class DetailItem {
    private static final DateFormat DATE_FORMAT = SimpleDateFormat.getDateInstance();
    final private Date mDate;

    public DetailItem(Date date) {
        mDate = date;
    }

    public static DetailItem parse(String s) throws ParseException {
        Date d = DATE_FORMAT.parse(s);
        return new DetailItem(d);
    }

    @Override
    public String toString() {
        return DATE_FORMAT.format(mDate);
    }
}
