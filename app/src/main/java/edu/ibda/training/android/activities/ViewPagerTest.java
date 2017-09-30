package edu.ibda.training.android.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import edu.ibda.training.android.Fragements.PagerFragment;
import edu.ibda.training.android.R;
import edu.ibda.training.android.adapters.ViewPagerFragmentAdapter;

/**
 * Created by DaiGooR on 9/30/2017.
 */

public class ViewPagerTest extends FragmentActivity {

    private ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view_pager);
        pager = (ViewPager) findViewById(R.id.viewpager);

//        // This comes from Android
//        getFragmentManager();
//
//        // This comes from support v4
//        getSupportFragmentManager();

        ViewPagerFragmentAdapter vpfa = new ViewPagerFragmentAdapter(getSupportFragmentManager() , getFragments());
        pager.setAdapter(vpfa);
    }

    private List<Fragment> getFragments () {
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(PagerFragment.getInstance("Fragments #1"));
        list.add(PagerFragment.getInstance("Fragments #2"));
        list.add(PagerFragment.getInstance("Fragments #3"));
        return list;
    }

}
