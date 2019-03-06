package com.bookmyticket.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bookmyticket.R;
import com.bookmyticket.interfaces.OnEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EventFragment extends Fragment {

    public OnEventListener eventListener;
    private int position;

    public EventFragment() {
    }

    public static Fragment newInstance(OnEventListener eventListener) {
        EventFragment f = new EventFragment();
        Bundle b = new Bundle();
        eventListener = eventListener;
        //mHud = hud;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       position = getArguments().getInt("position");
//        url = getArguments().getString("url");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.event_fragment, container,
                false);
        return rootView;
    }
}
