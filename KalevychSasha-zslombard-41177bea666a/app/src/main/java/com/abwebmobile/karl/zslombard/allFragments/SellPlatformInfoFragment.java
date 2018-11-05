package com.abwebmobile.karl.zslombard.allFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abwebmobile.karl.zslombard.R;

/**
 * Created by Karl on 07.02.2018.
 */

public class SellPlatformInfoFragment extends Fragment {
    public static SellPlatformInfoFragment newInstance() {
        return new SellPlatformInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sell_platform_info, container, false);
    }
}