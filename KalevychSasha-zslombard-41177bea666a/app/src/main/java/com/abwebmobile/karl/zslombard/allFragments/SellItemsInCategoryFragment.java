package com.abwebmobile.karl.zslombard.allFragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.InfoViewModel;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RoomEntities.SellItem;
import com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories.SellItemsAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.abwebmobile.karl.zslombard.Constants.ABOUT_ITEM_DIALOG_FRAGMENT;

/**
 * Created by Karl on 07.02.2018.
 */

public class SellItemsInCategoryFragment extends Fragment implements HostView {
    int categoryCode;
    RecyclerView recyclerView;
    InfoViewModel viewModel;
    SellItemsAdapter adapter;
ArrayList<SellItem> sellItemsList;
    public void setCategoryCode(int categoryCode) {

        this.categoryCode = categoryCode;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        viewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sell_items_in_category, container, false);
        sellItemsList = new ArrayList<>();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_sells_in_category);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(llm);
        final LiveData<List<SellItem>> sellItems = viewModel.getAllSellItems();
        if (sellItems.getValue() != null) {
            Log.d("sell", "after getItems called size = " + sellItems.getValue().size());
        }

        sellItems.observe(this, new Observer<List<SellItem>>() {
            @Override
            public void onChanged(@Nullable List<SellItem> items) {
                Log.d("sell", "ItemsChanged size = " + items.size());
                sellItemsList.addAll(items);
                setAdapter(items);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });


        return v;
    }

    private void setAdapter(List<SellItem> items) {
        adapter = new SellItemsAdapter(items, this);
    }

    @Override
    public void requestFragment(int fragmentCode, String value) {
        choosedItem = Integer.parseInt(value);
    if (fragmentCode==ABOUT_ITEM_DIALOG_FRAGMENT){
    AboutItemDialogFragment aboutDialog = new AboutItemDialogFragment();
    aboutDialog.setCurrentSellItem(sellItemsList.get(choosedItem));
    aboutDialog.show(getChildFragmentManager(),"");
    }
      }
int choosedItem = 0;


}