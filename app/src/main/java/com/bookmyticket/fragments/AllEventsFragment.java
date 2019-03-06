package com.bookmyticket.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bookmyticket.Data.LatestEventData;
import com.bookmyticket.Data.LatestMoviesData;
import com.bookmyticket.HomeActivity;
import com.bookmyticket.R;
import com.bookmyticket.adapters.AllPanelRecyclerAdapter;
import com.bookmyticket.adapters.SlidingImageAdapter;
import com.bookmyticket.interfaces.OnEventListener;
import com.bookmyticket.model.LatestMovieDataModel;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AllEventsFragment extends Fragment {

    public OnEventListener eventListener;
    private int position;

    private ArrayList<Integer> ImagesArray;
    private ViewPager mPager;

    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<LatestMovieDataModel> data;

    static View.OnClickListener myOnClickListener;

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

        //Latest Movies Recycler View


        myOnClickListener = new MyOnClickListener(getActivity());

        recyclerView = rootView.findViewById(R.id.LatestMovieRecyclerList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<LatestMovieDataModel>();
        for (int i = 0; i < LatestMoviesData.nameArray.length; i++) {
            data.add(new LatestMovieDataModel(
                    LatestMoviesData.nameArray[i],
                    LatestMoviesData.versionArray[i],
                    LatestMoviesData.id_[i],
                    LatestMoviesData.drawableArray[i]
            ));
        }

        adapter = new AllPanelRecyclerAdapter(data);
        recyclerView.setAdapter(adapter);

        //Latest Event Recycler View

        recyclerView = rootView.findViewById(R.id.LatestEventRecyclerList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<LatestMovieDataModel>();
        for (int i = 0; i < LatestEventData.nameArray.length; i++) {
            data.add(new LatestMovieDataModel(
                    LatestEventData.nameArray[i],
                    LatestEventData.versionArray[i],
                    LatestEventData.id_[i],
                    LatestEventData.drawableArray[i]
            ));
        }

        adapter = new AllPanelRecyclerAdapter(data);
        recyclerView.setAdapter(adapter);

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

    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < LatestMoviesData.nameArray.length; i++) {
                if (selectedName.equals(LatestMoviesData.nameArray[i])) {
                    selectedItemId = LatestMoviesData.id_[i];
                }
            }

        }
    }

}
