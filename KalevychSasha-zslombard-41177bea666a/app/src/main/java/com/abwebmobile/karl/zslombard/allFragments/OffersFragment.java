package com.abwebmobile.karl.zslombard.allFragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.abwebmobile.karl.zslombard.ButtonIncreaser;
import com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories.OffersAdapter;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RoomEntities.Offer;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.InfoViewModel;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.PayViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karl on 15.02.2018.
 *
 * Фрагмент, отвечающий за отображение списка договоров
 */

public class OffersFragment extends Fragment implements ButtonIncreaser {
    RecyclerView mRecyclerView;
    Button buttonCountOfOffersToPay, buttonAddCard;
    ImageButton buttonOffersChart;
    OffersAdapter mAdapter;
    ArrayList<Offer> mOffersList;
    InfoViewModel mViewModel;
    PayViewModel mPayViewModel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        mPayViewModel = ViewModelProviders.of(this).get(PayViewModel.class);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
        Log.d("test signed", "just empty string");

    }

    private void refreshTotal() {
        Log.d("refresh", "ok all is refreshing");
        mPayViewModel.removeAllOfferToPay();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_offers, container, false);
        mOffersList = new ArrayList<>();
        refreshTotal();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerOffers);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(llm);

        buttonCountOfOffersToPay = (Button)v.findViewById(R.id.buttonPayCurrentOffers);
                buttonCountOfOffersToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestDialogPay();
            }
        });

        buttonAddCard = (Button)v.findViewById(R.id.buttonAddCard);
        buttonAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestDialogAddCard();
            }
        });

        buttonOffersChart = (ImageButton)v.findViewById(R.id.buttonOffersChart);
        mOffersList.addAll(mViewModel.getLoginedUserOffers());
        setAdapter(mOffersList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        buttonOffersChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestDialogChart();
            }
        });

        return v;
    }

    private void requestDialogAddCard() {
        DialogAddCard dialogAddCard = new DialogAddCard();
        dialogAddCard.show(getChildFragmentManager(), "ДОДАВАННЯ КАРТИ");
    }

    private void requestDialogChart() {
        OffersChartDialogFragment offersChartDialogFragment = new OffersChartDialogFragment();
        offersChartDialogFragment.show(getChildFragmentManager(), "ГРАФІК КРЕДИТІВ");
        offersChartDialogFragment.setData(mOffersList);
    }

    private void setAdapter(List<Offer> items) {
        mAdapter = new OffersAdapter(items, this, getActivity().getApplicationContext());
    }



    public void requestDialogPay() {

        OfferPayDialogFragment offerPayDialogFragment = new OfferPayDialogFragment();
        offerPayDialogFragment.show(getChildFragmentManager(),"ОПЛАТА КРЕДИТУ");



    }

    @Override
    public void increaseButton(int requestedCount, Offer offerAddToPay, Float aFloat) {
        buttonCountOfOffersToPay.setText("ОПЛАТИТИ ФІКСОВАНІ  "+requestedCount);
        Log.d("float_control"," in frgament "+aFloat);
        mPayViewModel.addOfferToPay(offerAddToPay, aFloat);
    }

    @Override
    public void decreaseButton(int countCheckedOffers, Offer offer, Float aFloat) {
        buttonCountOfOffersToPay.setText("ОПЛАТИТИ ФІКСОВАНІ  "+countCheckedOffers);
        Log.d("float_control"," in frgament "+aFloat);
        mPayViewModel.removeOfferToPay(offer, aFloat);
    }
}
