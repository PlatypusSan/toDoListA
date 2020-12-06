package com.platy.todolist.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.platy.todolist.R;

import java.util.Date;

import okhttp3.internal.concurrent.Task;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    public static int pos;


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Date todayDate = new Date();
        Date tomorrowDate = new Date();
        tomorrowDate.setDate(todayDate.getDate() + 1);

        String today = (todayDate.getYear() + 1900) + "-" + todayDate.getMonth() + "-" + todayDate.getDate();
        String tomorrow = (tomorrowDate.getYear() + 1900) + "-" + tomorrowDate.getMonth() + "-" + tomorrowDate.getDate();


        switch (position) {
            case 0: return TaskListFragment.newInstance(mContext);
            case 1: return EventListFragment.newInstance(today, mContext);
            case 2: return EventListFragment.newInstance(tomorrow, mContext);

        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }

}