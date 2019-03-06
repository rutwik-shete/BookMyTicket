package com.bookmyticket;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.bookmyticket.adapters.ViewPagerAdapter;
import com.bookmyticket.fragments.AllEventsFragment;
import com.bookmyticket.fragments.EventFragment;
import com.bookmyticket.fragments.MoviesFragment;
import com.bookmyticket.interfaces.OnEventListener;
import com.bookmyticket.model.FragmentData;
import com.bookmyticket.utils.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements OnEventListener {

    com.flyco.tablayout.SlidingTabLayout tabview ;
    NonSwipeableViewPager pager;

    List<FragmentData>fragmentDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabview = findViewById(R.id.tabview);

        pager = findViewById(R.id.pager);

        fragmentDataList.add(new FragmentData(AllEventsFragment.newInstance(this),"All"));
        fragmentDataList.add(new FragmentData(MoviesFragment.newInstance(this),"Movies"));
        fragmentDataList.add(new FragmentData(EventFragment.newInstance(this),"Event"));
        pager.setPagingEnabled(true);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),this,fragmentDataList));
        tabview.setViewPager(pager);



    }

    @Override
    public void onEvent(int id) {

    }

    @Override
    public void onEvent(int id, Object object) {

    }
}
