package com.playground.sgaw.sample.datelogger.inputflow;

import android.widget.Toast;

/**
 * Presenter for user input screen.
 */

public class InputPresenter {
    private final IDateView mIDateView;
    private InputDataProvider mInputDataProvider = new InputDataProvider();

    public InputPresenter(IDateView dateView) {
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
}
