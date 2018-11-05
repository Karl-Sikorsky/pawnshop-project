package com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abwebmobile.karl.zslombard.AdressItem;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RecyclerViewClickListener;

import java.util.List;

/**
 * Created by Karl on 04.02.2018.
 */

public class AdressesRecyclerAdapter extends RecyclerView.Adapter<AdressesRecyclerAdapter.AdressesViewHolder> {

    public static class AdressesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView itemName;
        TextView itemDescription;
        ImageView arrow;

        AdressesViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemDescription = (TextView) itemView.findViewById(R.id.item_description);
            arrow = (ImageView) itemView.findViewById(R.id.arrow);


            itemView.setOnClickListener(this);

        }

        RecyclerViewClickListener recyclerViewClickListener;

        void setRecyclerViewClickListener(RecyclerViewClickListener listener) {
            this.recyclerViewClickListener = listener;
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListener.onClick(view, getAdapterPosition());
        }

    }

    private List<AdressItem> adressItemList;

    public AdressesRecyclerAdapter(List<AdressItem> items) {
        this.adressItemList = items;
    }

    @Override
    public int getItemCount() {
        if (adressItemList != null) {
            return adressItemList.size();
        } else return 0;
    }

    @Override
    public AdressesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adresses_item, parent, false);
        return new AdressesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdressesViewHolder holder, int position) {

        holder.itemName.setText(adressItemList.get(position).adress);
        holder.itemDescription.setText(adressItemList.get(position).description);
        if (adressItemList.get(position).isActive) {
            holder.itemDescription.setVisibility(View.VISIBLE);
            holder.arrow.setImageResource(R.drawable.ic_key_up);
        } else {
            holder.itemDescription.setVisibility(View.GONE);
            holder.arrow.setImageResource(R.drawable.ic_key_down);
        }
        holder.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("clickYes", String.valueOf(position));
                adressItemList.get(position).isActive = (!adressItemList.get(position).isActive);
                notifyDataSetChanged();
            }


        });
    }
}

