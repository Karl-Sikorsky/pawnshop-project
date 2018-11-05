package com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abwebmobile.karl.zslombard.ButtonIncreaser;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RecyclerViewClickListener;
import com.abwebmobile.karl.zslombard.RoomEntities.Offer;
import com.abwebmobile.karl.zslombard.allFragments.OffersFragment;

import java.util.List;

/**
 * Created by Karl on 15.02.2018.
 */

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {
         ButtonIncreaser hostView;
         int countCheckedOffers;
         Context context;
        public static class OffersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView offerID, creditBalance, accuraceInterest, surcharge, daysTheEnd, daysOverdue, operationDate, maxAmountPay, minAmountPay, offerDescription, offerDate, interestRatePerDay;
            EditText toPay;
            CheckBox checkBox;



            OffersViewHolder(View itemView) {
                super(itemView);
                offerID = (TextView)itemView.findViewById(R.id.textViewOfferID);
                creditBalance = (TextView)itemView.findViewById(R.id.textViewCreditBalance);
                accuraceInterest = (TextView)itemView.findViewById(R.id.textViewAccuredInterest);
                surcharge = (TextView)itemView.findViewById(R.id.textViewSurcharge);
                daysTheEnd = (TextView)itemView.findViewById(R.id.textViewDaysTheEnd);
                daysOverdue = (TextView)itemView.findViewById(R.id.textViewDaysOverdue);
                operationDate = (TextView)itemView.findViewById(R.id.textViewLastOperationDate);
                maxAmountPay = (TextView)itemView.findViewById(R.id.textViewMaximumAmountPayment);
                minAmountPay = (TextView)itemView.findViewById(R.id.textViewMinimumAmountPayment);
                offerDescription = (TextView)itemView.findViewById(R.id.textViewOfferDescription);
                offerDate = (TextView)itemView.findViewById(R.id.textViewOfferDate);
                interestRatePerDay = (TextView)itemView.findViewById(R.id.textViewInterestRatePerDay);
                toPay = (EditText)itemView.findViewById(R.id.EditTextToPay) ;
                checkBox = (CheckBox)itemView.findViewById(R.id.checkBox);


                checkBox.setOnClickListener(this);
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

        private List<Offer> offerItems;

        public OffersAdapter(List<Offer> items, OffersFragment hostView, Context context) {
            this.offerItems = items;
            this.hostView = hostView;
            countCheckedOffers=0;
            this.context = context;
        }

        @Override
        public int getItemCount() {
            if (offerItems != null) {
                return offerItems.size();
            } else return 0;
        }

        @Override
        public OffersAdapter.OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, parent, false);
            return new OffersAdapter.OffersViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final OffersAdapter.OffersViewHolder holder, int position) {

            holder.interestRatePerDay.setText("% кредиту: "+String.valueOf(offerItems.get(position).interestRatePerDay));
            holder.offerDate.setText(String.valueOf(offerItems.get(position).expirationDate));
            holder.offerDescription.setText(String.valueOf(offerItems.get(position).description));
            holder.minAmountPay.setText("min "+String.valueOf(offerItems.get(position).minimumAmountPayment));
            holder.maxAmountPay.setText("max "+String.valueOf(offerItems.get(position).maximumAmountPayment));
            holder.operationDate.setText("остання операція: "+String.valueOf(offerItems.get(position).lastOperationDate));
            holder.daysOverdue.setText(String.valueOf(offerItems.get(position).daysOverdue));
            holder.daysTheEnd.setText(String.valueOf(offerItems.get(position).daysTheEnd));
            holder.surcharge.setText(String.valueOf(offerItems.get(position).surcharge));
            holder.accuraceInterest.setText(String.valueOf(offerItems.get(position).accuredInterests));
            holder.creditBalance.setText(String.valueOf(offerItems.get(position).creditBalance));
            holder.offerID.setText("№"+String.valueOf(offerItems.get(position).contractNumber));
            holder.toPay.setText(String.valueOf(offerItems.get(position).minimumAmountPayment));



            holder.setRecyclerViewClickListener(new RecyclerViewClickListener() {
                @Override
                public void onClick(View view, int position) {

                    if(view.getId()==R.id.checkBox) {
                        try {
                            Log.d("toPayAriph", "value = " + (Float.valueOf(holder.toPay.getText().toString().trim())));
                            Log.d("toPayAriph", "max = " + offerItems.get(position).maximumAmountPayment);
                            Log.d("toPayAriph", "min = " + offerItems.get(position).minimumAmountPayment);
                            if (Float.valueOf(holder.toPay.getText().toString().trim()) > offerItems.get(position).maximumAmountPayment) {
                                Log.d("toPayAriph", "more than max about " + (Float.valueOf(holder.toPay.getText().toString()) - offerItems.get(position).maximumAmountPayment));
                                holder.toPay.setText(String.valueOf(offerItems.get(position).maximumAmountPayment));
                            } else if (Float.valueOf(holder.toPay.getText().toString()) < offerItems.get(position).minimumAmountPayment) {
                                Log.d("toPayAriph", "less than min about " + (Float.valueOf(holder.toPay.getText().toString()) - offerItems.get(position).minimumAmountPayment));
                                holder.toPay.setText(String.valueOf(offerItems.get(position).minimumAmountPayment));
                            }

                            if (!holder.checkBox.isChecked()) {
                                countCheckedOffers--;
                                holder.toPay.setEnabled(true);
                                hostView.decreaseButton(countCheckedOffers, offerItems.get(position), Float.valueOf(holder.toPay.getText().toString().trim()));
                            } else {

                                holder.toPay.setEnabled(false);
                                countCheckedOffers++;
                                Log.d("floats", holder.toPay.getText().toString());
                                hostView.increaseButton(countCheckedOffers, offerItems.get(position), Float.valueOf(holder.toPay.getText().toString().trim()));

                            }

                        }catch (NumberFormatException e){
                            Toast.makeText(context, "НЕПРАВИЛЬНО ВВЕДЕНІ ДАНІ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            });
        }





}
