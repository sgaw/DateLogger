package com.playground.sgaw.sample.datelogger.detailflow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.playground.sgaw.sample.datelogger.MainActivity;
import com.playground.sgaw.sample.datelogger.R;

/**
 * Show the details of a single logged item.
 */
public class DetailActivity extends AppCompatActivity {

    private DetailItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mAdapter = new DetailItemAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onStop() {
        super.onStop();

        mAdapter.save(this);
    }
}
