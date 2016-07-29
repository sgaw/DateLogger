package com.playground.sgaw.sample.datelogger.detailflow.detailitem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.playground.sgaw.sample.datelogger.R;

/**
 * View holder to for a {@link DetailItem}.
 */

public class DetailItemViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextView;

    public DetailItemViewHolder(View view) {
        super(view);
        mTextView = (TextView) view.findViewById(R.id.textView);
    }

    public void bindItem(DetailItem item) {
        mTextView.setText(item.toString());
    }

}
