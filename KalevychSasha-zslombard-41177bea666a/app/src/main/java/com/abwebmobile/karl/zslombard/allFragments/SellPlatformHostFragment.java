package com.abwebmobile.karl.zslombard.allFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;

import static com.abwebmobile.karl.zslombard.Constants.FRAGMENT_SHOP_CATEGORY;

/**
 * Created by Karl on 07.02.2018.
 */

public class SellPlatformHostFragment extends Fragment implements HostView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sell_platform_host, container, false);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                v.findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item_profile:
                                selectedFragment = SellPlatformAccountFragment.newInstance();
                                break;
                            case R.id.action_item_shop:
                                selectedFragment = setSelectedShop();

                                break;
                            case R.id.action_item_info:
                                selectedFragment = SellPlatformInfoFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
        bottomNavigationView.setSelectedItemId(R.id.action_item_shop);
        return v;
    }

    private SellPlatformShopFragment setSelectedShop() {
        return SellPlatformShopFragment.newInstance(this);
    }

    @Override
    public void requestFragment(int fragmentCode, String value) {
        if (fragmentCode == FRAGMENT_SHOP_CATEGORY) {
            SellItemsInCategoryFragment sellItemsInCategoryFragment = new SellItemsInCategoryFragment();
            sellItemsInCategoryFragment.setCategoryCode(categoryCode);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, sellItemsInCategoryFragment);
            transaction.commit();
        }
        ;
    }


    int categoryCode = 0;

}
