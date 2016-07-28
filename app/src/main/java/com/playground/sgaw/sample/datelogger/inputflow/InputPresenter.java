package com.playground.sgaw.sample.datelogger.inputflow;

/**
 * Presenter for user input screen.
 */

public class InputPresenter {
    private final IDateView mIDateView;
    private InputDataProvider mInputDataProvider;

    public InputPresenter(IDateView dateView) {
        mIDateView = dateView;
    }

    public void record() {
        mInputDataProvider.record();
    }

    public void progressDayOfMonth(int progress) {
        mInputDataProvider.setRelativeDayOfMonth(progress);
        mIDateView.setValue(mInputDataProvider.getDate());
    }
}
