package com.playground.sgaw.sample.datelogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.playground.sgaw.sample.datelogger.detailflow.DetailActivity;
import com.playground.sgaw.sample.datelogger.inputflow.InputPresenter;

public class MainActivity extends AppCompatActivity {
    private InputPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button launchButton = (Button) findViewById(R.id.detailButton);
        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetailView();
            }
        });

        CircularButton circularButton = (CircularButton) findViewById(R.id.circularButton);
        mPresenter = new InputPresenter(this, circularButton);
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

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.save(this);
    }

    private void launchDetailView() {
        startActivity(new Intent(this, DetailActivity.class));
        finish();
    }
}
