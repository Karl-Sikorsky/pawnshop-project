package com.abwebmobile.karl.zslombard.allFragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abwebmobile.karl.zslombard.ChatBotClasses.ChatBotDialogTrack;
import com.abwebmobile.karl.zslombard.ChatBotClasses.ChatBotMessage;
import com.abwebmobile.karl.zslombard.ChatBotClasses.ChatBotReply;
import com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories.MessagesAdapter;
import com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories.RepliesAdapter;
import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.InfoViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Karl on 01.03.2018.
 * Фрагмент, отвечающий за отображение чат-бота
 */

public class ChatBotFragment extends Fragment implements HostView {

    RecyclerView mRecyclerViewMessages;
    RecyclerView mRecyclerViewReplies;
    InfoViewModel mViewModel;
    MessagesAdapter mMessagesAdapter;
    RepliesAdapter mRepliesAdapter;
    ArrayList<ChatBotMessage> mMessages;
    ArrayList<ChatBotReply> mReplies;
    ChatBotReply mCurrentChatButton;
    ArrayList<ChatBotReply> mCurrentReplies;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat_bot, container, false);

        mMessages = new ArrayList<>();
        mReplies = new ArrayList<>();
        mRecyclerViewMessages = (RecyclerView) v.findViewById(R.id.recyclerMessages);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerViewMessages.setLayoutManager(llm);
        mRecyclerViewReplies = (RecyclerView) v.findViewById(R.id.recyclerReplies);
        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerViewReplies.setLayoutManager(llm2);
        mCurrentChatButton = new ChatBotReply(getString(R.string.chat_bot_button_intro),0, 10000, new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)));;
//init replies and messages for chat bot
        LiveData<List<ChatBotReply>> repliesTexts = mViewModel.getRepliesItems();
        if (repliesTexts.getValue() != null) {
            Log.d("sell", "after getItems called size = " + repliesTexts.getValue().size());
        }
        repliesTexts.observe(this, new Observer<List<ChatBotReply>>() {
            @Override
            public void onChanged( List<ChatBotReply> items) {
                Log.d("chatbot", "repliesTexts changed = "+items.size());
                Log.d("replies", "have in observer = " + items.size());
                mReplies.addAll(items);
                setRepliesAdapter(items);
                mRecyclerViewReplies.setAdapter(mRepliesAdapter);
                mRepliesAdapter.notifyDataSetChanged();
            }
        });

        LiveData<List<ChatBotMessage>> messageItems = mViewModel.getMessageItems();
        if (messageItems.getValue() != null) {
            Log.d("sell", "after getItems called size = " + messageItems.getValue().size());
        }

        messageItems.observe(this, new Observer<List<ChatBotMessage>>() {
            @Override
            public void onChanged( List<ChatBotMessage> items) {
                Log.d("sell", "ItemsChanged size = " + items.size());
                mMessages.addAll(items);
                setMessagesAdapter(items);
                mRecyclerViewMessages.setAdapter(mMessagesAdapter);
                mMessagesAdapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    private void setMessagesAdapter(List<ChatBotMessage> items) {
        Log.d("replies", "called setMessagesAdapter with size = " + items.size());

        ArrayList<ChatBotMessage> messages = new ArrayList<>();
        for(int i = 0;i<items.size();i++)
        {
            if (mCurrentChatButton.text_code==items.get(i).code)messages.add(items.get(i));
        }
        mMessagesAdapter = new MessagesAdapter(messages, this);
        mRecyclerViewMessages.setAdapter(mMessagesAdapter);
        mMessagesAdapter.notifyDataSetChanged();
    }

    private void setRepliesAdapter(List<ChatBotReply> items) {
        mCurrentReplies = new ArrayList<>();
        for (int i=0; i<items.size();i++){
            if (mCurrentChatButton.next_buttons.contains(items.get(i).self_code) )mCurrentReplies.add(items.get(i));
        }
        Log.d("chatbot", "adapter setted = "+items.size());
        Log.d("replies", "have currented in setAdapter = " + mCurrentReplies.size());
        mCurrentReplies.add( new ChatBotReply(getString(R.string.chat_bot_button_return), 0, 10000, new ArrayList<Integer>(Arrays.asList(1,2,3,4,5) )));
        mRepliesAdapter = new RepliesAdapter(mCurrentReplies, this);
        mRepliesAdapter.notifyDataSetChanged();
        setMessagesAdapter(mMessages);
        mRecyclerViewReplies.setAdapter(mRepliesAdapter);
        mRepliesAdapter.notifyDataSetChanged();
    }

    // if replies will request map or call for operator

    @Override
    public void requestFragment(int fragmentCode, String value) {
        Log.d("replies", "called request fragment with code =  " + fragmentCode);
        if (value.equals("ChangeButton")){
            mCurrentChatButton = mCurrentReplies.get(fragmentCode);
            setRepliesAdapter(mReplies);
        }
        if(mCurrentChatButton.self_code == 3){
            String phone = "+380685180997";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        }
        if (mCurrentChatButton.self_code == 2){
            ChatBotDialogTrack trackDialog = new ChatBotDialogTrack();

            trackDialog.show(getChildFragmentManager(),"");
        }
    }



}