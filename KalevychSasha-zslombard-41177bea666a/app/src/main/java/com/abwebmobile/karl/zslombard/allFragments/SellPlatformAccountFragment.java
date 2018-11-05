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
import android.widget.EditText;

import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.InfoViewModel;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RoomEntities.SellItem;
import com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories.SellItemsAdapter;

import java.util.List;

import static com.abwebmobile.karl.zslombard.Constants.ABOUT_ITEM_DIALOG_FRAGMENT;

/**
 * Created by Karl on 07.02.2018.
 */

public class SellPlatformAccountFragment extends Fragment implements HostView {
    public static SellPlatformAccountFragment newInstance() {
        SellPlatformAccountFragment fragment = new SellPlatformAccountFragment();
        return fragment;
    }
    EditText name, phone, login, password, mail;
    RecyclerView recyclerView;
    InfoViewModel viewModel;
    SellItemsAdapter adapter;
    LiveData<List<SellItem>> sellItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
setRetainInstance(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sell_platform_account, container, false);
        name = (EditText)v.findViewById(R.id.editTextUserName);
        login = (EditText)v.findViewById(R.id.editTextUserLogin);
        phone = (EditText)v.findViewById(R.id.editTextUserPhone);
        password = (EditText)v.findViewById(R.id.editTextUserPassword);
        mail = (EditText)v.findViewById(R.id.editTextUserMail);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_history_sells);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(llm);
        sellItems = viewModel.getAllSellItems();
        if(sellItems.getValue()!=null){
            Log.d("sell","after getItems called size = "+sellItems.getValue().size());}

        sellItems.observe(this, new Observer<List<SellItem>>() {
            @Override
            public void onChanged(@Nullable List<SellItem> items) {
                Log.d("sell","ItemsChanged size = "+items.size());
                setAdapter(items);

                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        return v;

    }

    private void setAdapter(List<SellItem> items) {
        adapter  = new SellItemsAdapter(items, this);
    }



    //not sure about this code

    @Override
    public void requestFragment(int fragmentCode, String value) {
        int choosedItem = Integer.parseInt(value);
        if (fragmentCode==ABOUT_ITEM_DIALOG_FRAGMENT){
            AboutItemDialogFragment aboutDialog = new AboutItemDialogFragment();
            aboutDialog.setCurrentSellItem(sellItems.getValue().get(choosedItem));
            aboutDialog.show(getChildFragmentManager(),"");
        }
    }


}