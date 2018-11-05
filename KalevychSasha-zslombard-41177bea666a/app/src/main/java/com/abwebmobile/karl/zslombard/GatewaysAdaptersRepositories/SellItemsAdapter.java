package com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RecyclerViewClickListener;
import com.abwebmobile.karl.zslombard.RoomEntities.SellItem;

import java.util.List;

import static com.abwebmobile.karl.zslombard.Constants.ABOUT_ITEM_DIALOG_FRAGMENT;


/**
 * Created by Karl on 07.02.2018.
 */

public class SellItemsAdapter extends RecyclerView.Adapter<SellItemsAdapter.SellItemsViewHolder> {
HostView hostView;

        public static class SellItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            CardView cv;
            TextView itemName, itemPrice, ownerName, ownerPhone;
            TextView itemDescription;
            ImageView itemPic;



            SellItemsViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.cv);
                itemName = (TextView) itemView.findViewById(R.id.textViewItemName);
                itemDescription = (TextView) itemView.findViewById(R.id.textViewItemDescription);
                itemPic = (ImageView) itemView.findViewById(R.id.imageViewItemPic);
                itemPrice = (TextView)  itemView.findViewById(R.id.textViewItemPrice);
                ownerName = (TextView) itemView.findViewById(R.id.textViewOwnerName);
                ownerPhone = (TextView) itemView.findViewById(R.id.textViewOwnerPhone);


                itemView.setOnClickListener(this);

            }

            private RecyclerViewClickListener recyclerViewClickListener;

            void setRecyclerViewClickListener(RecyclerViewClickListener listener) {
                this.recyclerViewClickListener = listener;
            }

            @Override
            public void onClick(View view) {
                recyclerViewClickListener.onClick(view, getAdapterPosition());
            }

        }

        private List<SellItem> sellItems;

        public SellItemsAdapter(List<SellItem> items, HostView hostView) {
            this.sellItems = items;
            this.hostView = hostView;
        }

        @Override
        public int getItemCount() {
            if (sellItems != null) {
                return sellItems.size();
            } else return 0;
        }

        @Override
        public SellItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sell_item_card, parent, false);
            return new SellItemsViewHolder(v);
        }

        @Override
        public void onBindViewHolder(SellItemsViewHolder holder, int position) {

            holder.itemName.setText(sellItems.get(position).name);
            holder.itemDescription.setText(sellItems.get(position).description);
           holder.itemPrice.setText(sellItems.get(position).price_presentation);
           //holder.itemPic.hmmmm
            holder.ownerName.setText(sellItems.get(position).city);
            holder.ownerPhone.setText(sellItems.get(position).adress);

            holder.setRecyclerViewClickListener(new RecyclerViewClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Log.d("clickYes", String.valueOf(position));

                    hostView.requestFragment(ABOUT_ITEM_DIALOG_FRAGMENT, String.valueOf(position));

                    notifyDataSetChanged();
                }


            });
        }



}
