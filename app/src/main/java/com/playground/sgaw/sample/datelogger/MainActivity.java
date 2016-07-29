package com.playground.sgaw.sample.datelogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.playground.sgaw.sample.datelogger.detailflow.DetailActivity;
import com.playground.sgaw.sample.datelogger.inputflow.InputPresenter;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private InputPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button launchButton = (Button) findViewById(R.id.detailButton);
        final MainActivity activity = this;
        launchButton.setOnClickListener(new View.OnClickListener() {
            final WeakReference<MainActivity> mActivityRef = new WeakReference<>(activity);

            @Override
            public void onClick(View view) {
                mActivityRef.get().launchDetailView();
            }
        });

        Button recordButton = (Button) findViewById(R.id.recordButton);
        mPresenter = new InputPresenter(new DateView(recordButton));
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.record();
                Toast.makeText(view.getContext(),
                        R.string.toast_click_record,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean userInput) {
                if (userInput) {
                    mPresenter.progressDayOfMonth(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void launchDetailView() {
        startActivity(new Intent(this, DetailActivity.class));
        finish();
    }

    private static class DateView implements IDateView {
        private final Button mButton;
        private final SimpleDateFormat mFormat;

        private DateView(Button button) {
            mButton = button;
            mFormat = new SimpleDateFormat("MMM d");
        }

        @Override
        public void setValue(Date value) {
            // TODO: format string
            mButton.setText(mFormat.format(value));
        }
    }
}
