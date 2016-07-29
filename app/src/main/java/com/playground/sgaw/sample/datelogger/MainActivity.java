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

        CircularButton circularButton = (CircularButton) findViewById(R.id.circularButton);
        mPresenter = new InputPresenter(circularButton);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.record();
                Toast.makeText(view.getContext(),
                        R.string.toast_click_record,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        };
        circularButton.setOnClickListener(clickListener);


        CircularSeekBar seekBar = (CircularSeekBar) findViewById(R.id.seekBar);
        seekBar.setProgressChangedListener(new CircularSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(CircularSeekBar seekBar, int progress) {
                mPresenter.progressDayOfMonth(progress);
            }
        });
    }

    private void launchDetailView() {
        startActivity(new Intent(this, DetailActivity.class));
        finish();
    }
}
