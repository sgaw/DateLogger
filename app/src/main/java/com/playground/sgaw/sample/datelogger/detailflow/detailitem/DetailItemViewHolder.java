package com.playground.sgaw.sample.datelogger.detailflow.detailitem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.playground.sgaw.sample.datelogger.IDateView;
import com.playground.sgaw.sample.datelogger.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * View holder to for a {@link DetailItem}.
 */

public class DetailItemViewHolder extends RecyclerView.ViewHolder implements IDateView {
    TextView mTextView;
    SimpleDateFormat mFormat;

    public DetailItemViewHolder(View view) {
        super(view);
        mTextView = (TextView) view.findViewById(R.id.textView);
        mFormat = new SimpleDateFormat("MMM d");
    }

    public void bindItem(DetailItem item) {
        setValue(item.getDate());
    }

    @Override
    public void setValue(Date value) {
        mTextView.setText(mFormat.format(value));
    }
}
