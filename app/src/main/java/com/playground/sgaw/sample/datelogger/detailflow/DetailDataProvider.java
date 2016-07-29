package com.playground.sgaw.sample.datelogger.detailflow;

import android.support.v4.util.CircularArray;

import com.playground.sgaw.sample.datelogger.detailflow.detailitem.DetailItem;

import java.util.Calendar;
import java.util.Date;

/**
 * Data provider for all items shown in the detail view.
 */

public class DetailDataProvider {
    // TODO: use Dagger2 for singleton management
    private static DetailDataProvider sInstance = new DetailDataProvider();

    private CircularArray<DetailItem> mDetailItemList;

    private DetailDataProvider() {
        this(new CircularArray<DetailItem>());
    }

    private DetailDataProvider(CircularArray<DetailItem> detailItemList) {
        mDetailItemList = detailItemList;
    }

    public static DetailDataProvider getInstance() {
        return sInstance;
    }

    public void addDetailItem(Date date) {
        mDetailItemList.addLast(new DetailItem(date));
    }

    DetailItem getDetailItem(int position) {
        return mDetailItemList.get(position);
    }

    int getDetailItemCount() {
        return mDetailItemList.size();
    }
}
