package com.bookmyticket.model;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class FragmentData implements Serializable {
    private Fragment fragment;
    private String title;

    public FragmentData(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
