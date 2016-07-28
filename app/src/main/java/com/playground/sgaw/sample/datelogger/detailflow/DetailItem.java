package com.playground.sgaw.sample.datelogger.detailflow;

import java.util.Date;

/**
 * Model class to represent the logged date.
 */
public class DetailItem {
    final private Date mDate;

    public DetailItem(Date date) {
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }
}
