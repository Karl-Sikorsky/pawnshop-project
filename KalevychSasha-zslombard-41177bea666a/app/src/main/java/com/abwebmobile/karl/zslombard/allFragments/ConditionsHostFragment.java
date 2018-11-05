package com.abwebmobile.karl.zslombard.allFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karl on 04.02.2018.
 * Фрагмент, который хостит фрагменты условий кредитирования и отображает их в NavTabs
 */

public class ConditionsHostFragment extends android.support.v4.app.Fragment {
    HostView mHostView;
    ViewPager mViewPager;
    public void setHostView(HostView hostView) {
        this.mHostView = hostView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentTab", mViewPager.getCurrentItem());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_conditions_host,container, false);
        // Setting ViewPager for each Tabs
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.conditions_tabs);
        tabs.setupWithViewPager(mViewPager);

        if (savedInstanceState!=null){
            mViewPager.setCurrentItem(savedInstanceState.getInt("currentTab"));
        }

        return view;

    }


    // Add Fragments to Tabs

    private void setupViewPager(ViewPager viewPager) {


        Adapter adapter = new Adapter(getChildFragmentManager());
        ConditionsJewerlyFragment jewerly = new ConditionsJewerlyFragment();
        jewerly.setHostView(mHostView);
        adapter.addFragment(jewerly, getString(R.string.jewerly_tab_head));
        adapter.addFragment(new ConditionsAppliancesFragment(), getString(R.string.appliance_tab_head));

        viewPager.setAdapter(adapter);



    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();



        Adapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



}

