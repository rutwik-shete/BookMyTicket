package com.bookmyticket.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bookmyticket.R;
import com.bookmyticket.adapters.SlidingImageAdapter;
import com.bookmyticket.interfaces.OnEventListener;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AllEventsFragment extends Fragment {

    public OnEventListener eventListener;
    private int position;

    ViewFlipper ImageFlipper ;

    private ArrayList<Integer> ImagesArray;
    private ViewPager mPager;

    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    public AllEventsFragment() {
    }

    public static Fragment newInstance(OnEventListener eventListener) {
        AllEventsFragment f = new AllEventsFragment();
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

        View rootView = inflater.inflate(R.layout.all_event_fragment, container,
                false);

        int images[] = {R.drawable.page1,R.drawable.page2,R.drawable.page3};
        ImagesArray = new ArrayList<Integer>();


        init(images,rootView);
        return rootView;
    }


    private void init(int images[] , View rootView) {

        for(int i=0;i<images.length;i++)
            ImagesArray.add(images[i]);

        mPager = rootView.findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImageAdapter(getActivity(),ImagesArray));


        WormDotsIndicator indicator = rootView.findViewById(R.id.worm_dots_indicator);

        indicator.setViewPager(mPager);

       // final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        //indicator.setRadius(5 * density);

        NUM_PAGES =images.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 3000);

        // Pager listener over indicator
        /*indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });*/

    }
}
