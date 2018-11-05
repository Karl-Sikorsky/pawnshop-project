package com.abwebmobile.karl.zslombard.allFragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.InfoViewModel;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RoomEntities.Category;

import java.util.List;

import static com.abwebmobile.karl.zslombard.Constants.FRAGMENT_SHOP_CATEGORY;

/**
 * Created by Karl on 07.02.2018.
 */

public class SellPlatformShopFragment extends Fragment {
    public static SellPlatformShopFragment newInstance(HostView hostView) {

        SellPlatformShopFragment sellF = new SellPlatformShopFragment();
        sellF.setHostView(hostView);
        return sellF;
    }

ListView sellCategories;
    InfoViewModel viewModel;
    HostView hostView;

    public void setHostView(HostView hostView) {
        this.hostView = hostView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
       // viewModel.addDefaultCategories();
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sell_platform_shop, container, false);
        sellCategories = (ListView)v.findViewById(R.id.listViewSellCategories);
        LiveData<List<Category>> categories = viewModel.getSellCategories();
        if(categories.getValue()!=null){
            Log.d("sell","after getItems called size = "+categories.getValue().size());}

        categories.observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> itemsCat) {
                if(itemsCat != null) {

                    Log.d("sell", "ItemsChanged size = " + itemsCat.size());
                    String[] items = new String[itemsCat.size()];
                    for(int i=0;i<itemsCat.size();i++)items[i]=itemsCat.get(i).category_name;

                    //ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, items);
                    sellCategories.setAdapter(new ArrayAdapter<String>(
                            getActivity().getApplicationContext(), R.layout.list_black_text, R.id.list_content, items));

                }
            }
        });
        sellCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                hostView.requestFragment(FRAGMENT_SHOP_CATEGORY, String.valueOf(position));
            }
        });




      return v;
    }
}