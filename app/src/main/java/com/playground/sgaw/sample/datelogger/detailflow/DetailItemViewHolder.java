package com.playground.sgaw.sample.datelogger.detailflow;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.playground.sgaw.sample.datelogger.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * View holder to for a {@link DetailItem}.
 */

public class DetailItemViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;
    SimpleDateFormat mFormat;

    public DetailItemViewHolder(View view) {
        super(view);
        mTextView = (TextView) view.findViewById(R.id.textView);
        mFormat = new SimpleDateFormat("MMM d");
    }

    void bindItem(DetailItem item) {
        mTextView.setText(mFormat.format(item.getDate()));
    }

}
