package com.playground.sgaw.sample.datelogger.inputflow;

import android.app.Activity;

import com.playground.sgaw.sample.datelogger.IDateView;
import com.playground.sgaw.sample.datelogger.MainActivity;

/**
 * Presenter for user input screen.
 */

public class InputPresenter {
    private final IDateView mIDateView;
    private InputDataProvider mInputDataProvider;

    public InputPresenter(Activity activity, IDateView dateView) {
        mInputDataProvider = new InputDataProvider(activity);
        mIDateView = dateView;
        mIDateView.setValue(mInputDataProvider.getDate());
    }

    public void record() {
        mInputDataProvider.record();
    }

    public void progressDayOfMonth(int progress) {
        mInputDataProvider.setRelativeDayOfMonth(progress);
        mIDateView.setValue(mInputDataProvider.getDate());
    }

    public void save(Activity activity) {
        mInputDataProvider.save(activity);
    }
}
