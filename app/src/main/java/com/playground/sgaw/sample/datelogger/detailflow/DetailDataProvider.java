package com.playground.sgaw.sample.datelogger.detailflow;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.util.CircularArray;
import android.util.Log;

import com.playground.sgaw.sample.datelogger.detailflow.detailitem.DetailItem;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Data provider for all items shown in the detail view.
 */

public class DetailDataProvider {
    public static final String PREFERENCES_NAME = "com.playground.sgaw.sample.datelogger";
    private static final String PREFERENCE_DETAILS = "Details";

    // TODO: use Dagger2 for singleton management
    private static DetailDataProvider sInstance = new DetailDataProvider();

    private CircularArray<DetailItem> mDetailItemList;
    private boolean mIsLoaded = false;

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

    public void load(Activity activity) {
        // Avoid multiple loads when activities restart (could be done from an Application class)
        if (mIsLoaded) {
            return;
        }

        SharedPreferences preferences = getSharedPreferences(activity);
        Set<String> in = preferences.getStringSet(PREFERENCE_DETAILS, null);
        if (in == null) {
            return;
        }

        mDetailItemList.clear();
        for (String s : in) {
            try {
                mDetailItemList.addLast(DetailItem.parse(s));
            } catch (ParseException e) {
                Log.e("DetailDataProvider", String.format("Bad serialized item: %s", s), e);
            }
        }
        mIsLoaded = true;
    }

    public void save(Activity activity) {
        SharedPreferences preferences = getSharedPreferences(activity);
        preferences.edit()
                .putStringSet(PREFERENCE_DETAILS, serialize())
                .apply();
    }

    private SharedPreferences getSharedPreferences(Activity activity) {
        return activity.getSharedPreferences(PREFERENCES_NAME, 0);
    }

    private Set<String> serialize() {
        int size = mDetailItemList.size();
        HashSet<String> out = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            out.add(mDetailItemList.get(i).toString());
        }
        return out;
    }
}
