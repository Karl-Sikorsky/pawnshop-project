package com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abwebmobile.karl.zslombard.ChatBotClasses.ChatBotMessage;
import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;


import java.util.List;

/**
 * Created by Karl on 01.03.2018.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {
    private HostView mHostView;

    public static class MessagesViewHolder extends RecyclerView.ViewHolder {
        TextView messageView;
        MessagesViewHolder(View itemView) {
            super(itemView);
           messageView = (TextView)itemView.findViewById(R.id.message_view) ;
        }
    }

    private List<ChatBotMessage> messages;

    public MessagesAdapter(List<ChatBotMessage> items, HostView hostView) {
        this.messages = items;
        this.mHostView = hostView;
    }

    @Override
    public int getItemCount() {
        if (messages != null) {
            return messages.size();
        } else return 0;
    }

    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_bot_recycler_message, parent, false);
        return new MessagesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MessagesViewHolder holder, int position) {

        holder.messageView.setText(messages.get(position).text);



    }



}
