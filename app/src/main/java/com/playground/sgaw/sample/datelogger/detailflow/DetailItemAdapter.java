package com.playground.sgaw.sample.datelogger.detailflow;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playground.sgaw.sample.datelogger.R;
import com.playground.sgaw.sample.datelogger.detailflow.DetailDataProvider;
import com.playground.sgaw.sample.datelogger.detailflow.detailitem.DetailItem;
import com.playground.sgaw.sample.datelogger.detailflow.detailitem.DetailItemViewHolder;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * RecyclerView.Adapter to bind {@link DetailItem} to views.
 */

public class DetailItemAdapter extends RecyclerView.Adapter<DetailItemViewHolder> {
    final DetailDataProvider mDataProvider = DetailDataProvider.getInstance();

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

    public void save(Activity activity) {
        mDataProvider.save(activity);
    }
}
