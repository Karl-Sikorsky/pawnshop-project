package com.abwebmobile.karl.zslombard.ChatClasses;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.allFragments.ConsultationRequestDialog;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Date;

/**
 * Created by Karl on 13.03.2018.
 */

public class ChatFragment extends Fragment {

    private FirebaseListAdapter<ChatMessage> adapter;
    String reference;
    String chatDate;

    public void setChatDate(String chatDate) {
        this.chatDate = chatDate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState!=null) {
          chatDate = savedInstanceState.getString("chatDate");
        }


    }
    View v;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("chatDate", chatDate);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_chat, container, false);


        displayChatMessages();


        FloatingActionButton fab =
                (FloatingActionButton)v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)v.findViewById(R.id.input);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance()
                        .getReference(chatDate)
                        .push()
                        .setValue(new ChatMessage(input.getText().toString(),
                                "User")
                        );

                // Clear the input
                input.setText("");
            }
        });

        return v;
    }

    private void displayChatMessages() {
        ListView listOfMessages = (ListView)v.findViewById(R.id.list_of_messages);
        Query query = FirebaseDatabase.getInstance().getReference().child(chatDate);
        FirebaseListOptions<ChatMessage> options =
                new FirebaseListOptions.Builder<ChatMessage>()
                        .setQuery(query, ChatMessage.class)
                        .setLayout(R.layout.message)
                        .setLifecycleOwner(getActivity())
                        .build();
        adapter = new FirebaseListAdapter<ChatMessage>(options) {
            @Override
            protected void populateView(View view, ChatMessage model, int position) {
                TextView messageText = (TextView) view.findViewById(R.id.message_text);
                TextView messageUser = (TextView) view.findViewById(R.id.message_user);
                TextView messageTime = (TextView) view.findViewById(R.id.message_time);

                // Set their text
                try {
                    messageText.setText(model.getMessageText());
                    messageUser.setText(model.getMessageUser());

                    // Format the date before showing it
                    messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                            model.getMessageTime()));
                    Log.d("populateCalled", model.getMessageText());
                }catch (Exception e){e.printStackTrace();};
            }
        };


        listOfMessages.setAdapter(adapter);
    }
}