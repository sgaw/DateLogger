package com.playground.sgaw.sample.datelogger.detailflow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playground.sgaw.sample.datelogger.R;

/**
 * RecyclerView.Adapter to bind {@link DetailItem} to views.
 */

public class DetailItemAdapter extends RecyclerView.Adapter<DetailItemViewHolder> {
    DetailDataProvider mDataProvider = DetailDataProvider.getInstance();

    @Override
    public DetailItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_item, parent, false);
        return new DetailItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailItemViewHolder holder, int position) {
        holder.bindItem(mDataProvider.getDetailItem(position));
    }

    @Override
    public int getItemCount() {
        return mDataProvider.getDetailItemCount();
    }
}
