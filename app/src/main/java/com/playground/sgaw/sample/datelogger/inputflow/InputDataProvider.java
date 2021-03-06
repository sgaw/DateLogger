package com.playground.sgaw.sample.datelogger.inputflow;

import android.app.Activity;

import com.playground.sgaw.sample.datelogger.detailflow.DetailDataProvider;

import java.util.Calendar;
import java.util.Date;

/**
 * Data provider for any model classes needed for the input view.
 */
public class InputDataProvider {
    private Calendar mCalendar;
    private DetailDataProvider mDetailDataProvider = DetailDataProvider.getInstance();

    public InputDataProvider(Activity activity) {
        this(activity, Calendar.getInstance());
    }

    private InputDataProvider(Activity activity, Calendar instance) {
        mDetailDataProvider.load(activity);
        mCalendar = instance;
        mCalendar.set(2016, 00, 01);
    }


    Date getDate() {
        return mCalendar.getTime();
    }

    void record() {
        mDetailDataProvider.addDetailItem(getDate());
    }

    void setRelativeDayOfMonth(int percent) {
        int maxDayOfMonth = mCalendar.getMaximum(Calendar.DAY_OF_MONTH);
        int dayOfMonth = (int) Math.round(percent * maxDayOfMonth / 100.0);
        if (dayOfMonth < 1) {
            dayOfMonth = 1;
        }
        setDayOfMonth(dayOfMonth);
    }

    private void setDayOfMonth(int dayOfMonth) {
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    public void save(Activity activity) {
        mDetailDataProvider.save(activity);
    }
}
