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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RoomEntities.NewsItem;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.InfoViewModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import static com.abwebmobile.karl.zslombard.Constants.NEWS_FRAGMENT;

/**
 * Created by Karl on 05.02.2018.
 * Диалог, отображающий карусель новостей
 *
 *
 */

public class NewsFragment extends Fragment {
    CarouselView carouselView;
    InfoViewModel mViewModel;
    ArrayList<String> mNewsUrlsList;
    LiveData<List<NewsItem>> mNews;
    HostView mHostView;

    public void setHostView(HostView hostView) {
        this.mHostView = hostView;
    }

    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};
    //int[] sampleImages;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
      //  viewModel.addDefaultNews();
      // viewModel.addDefaultTimestamp();
        mViewModel.checkTimeStamp();
        mNews = mViewModel.getNews();
        if(mNews.getValue()!=null){
            Log.d("news","after getItems called size = "+mNews.getValue().size());}
        carouselView = (CarouselView)v.findViewById(R.id.carouselView);
        mNews.observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable final List<NewsItem> itemsNews) {
                if(itemsNews != null) {
                    if (itemsNews.size()==0){mViewModel.addDefaultNews();

                        mHostView.requestFragment(NEWS_FRAGMENT,"");
                    }
                    Log.d("loadedImgs ","in DB size is "+itemsNews.size());
                    mNewsUrlsList = new ArrayList<>();
                    for(int i=0;i<itemsNews.size();i++){
                        mNewsUrlsList.add(itemsNews.get(i).image_url);
                    }
                    String[] stockArr = new String[mNewsUrlsList.size()];
                    stockArr = mNewsUrlsList.toArray(stockArr);



                    final String[] finalStockArr = stockArr;
                    carouselView.setImageListener(new ImageListener() {
                        @Override
                        public void setImageForPosition(int position, ImageView imageView) {
                            //TODO: DRAW IMAGE
                            Glide.with(getActivity().getApplicationContext())
                                    .load(itemsNews.get(position).image_url)
                                    .into(imageView);
                            Log.d("loadedImgs", itemsNews.get(position).image_url);
                        }
                    });
                    carouselView.setPageCount(itemsNews.size());
                    carouselView.setImageClickListener(new ImageClickListener() {
                        @Override
                        public void onClick(int position) {
                            String description =  mNews.getValue().get(position).news_description;
                            NewsDescriptionDialogFragment newsDescriptionDialogFragment = new NewsDescriptionDialogFragment();
                            newsDescriptionDialogFragment.setDescription(description);
                            newsDescriptionDialogFragment.show(getChildFragmentManager(), "ВІДПРАВИТИ ЗАЯВКУ");
                        }});
                }else{


                    carouselView.setImageListener(imageListener);
                    carouselView.setPageCount(sampleImages.length);
                    carouselView.setImageClickListener(new ImageClickListener() {
                        @Override
                        public void onClick(int position) {
                           String description =  mNews.getValue().get(position).news_description;
                            NewsDescriptionDialogFragment newsDescriptionDialogFragment = new NewsDescriptionDialogFragment();
                            newsDescriptionDialogFragment.setDescription(description);
                            newsDescriptionDialogFragment.show(getChildFragmentManager(), "ВІДПРАВИТИ ЗАЯВКУ");
                            }
                    });
                }


            }
        });


        return v;
    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}
