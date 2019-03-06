package com.bookmyticket.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bookmyticket.fragments.AllEventsFragment;
import com.bookmyticket.model.FragmentData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Aikya on 05-Oct-18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    Context context;
    LayoutInflater inflater;
    private Fragment currentFragment;
    List<FragmentData>fragmentList = new ArrayList<>();
    private AllEventsFragment fragment;

    public ViewPagerAdapter(FragmentManager fm, Context context, List<FragmentData>fragmentList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
    }



    @Override
    public Fragment getItem(int position) {
        currentFragment = fragmentList.get(position).getFragment();
        return fragmentList.get(position).getFragment();



    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (fragmentList != null) {
            count = fragmentList.size();
        }
        return count;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return fragmentList.get(position).getTitle();
    }

}