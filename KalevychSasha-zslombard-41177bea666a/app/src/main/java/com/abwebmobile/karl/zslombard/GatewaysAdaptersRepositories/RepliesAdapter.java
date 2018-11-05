package com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abwebmobile.karl.zslombard.ChatBotClasses.ChatBotReply;
import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RecyclerViewClickListener;

import java.util.List;

/**
 * Created by Karl on 01.03.2018.
 */

public class RepliesAdapter  extends RecyclerView.Adapter<RepliesAdapter.RepliesViewHolder> {
    HostView hostView;

    public static class RepliesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button replyView;



        RepliesViewHolder(View itemView) {
            super(itemView);
            replyView = (Button) itemView.findViewById(R.id.buttonChatReply) ;



          replyView.setOnClickListener(this);
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

    private List<ChatBotReply> repliesTexts;

    public RepliesAdapter(List<ChatBotReply> items, HostView hostView) {
        this.repliesTexts = items;
        this.hostView = hostView;
        Log.d("chatbot", "adapter created with size = "+items.size());
    }

    @Override
    public int getItemCount() {
        if (repliesTexts != null) {
            return repliesTexts.size();
        } else return 0;
    }

    @Override
    public RepliesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_bot_recycler_reply, parent, false);
        Log.d("chatbot", "viewholder created");
        return new RepliesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RepliesViewHolder holder, int position) {
        Log.d("replies", "OnBind Called = ");

        holder.replyView.setText(repliesTexts.get(position).text);
        Log.d("chatbot", "one reply setted");

        holder.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Log.d("replies", "in adapter try to call SET NEW ");

                hostView.requestFragment(position, "ChangeButton");

            }});


    }



}
