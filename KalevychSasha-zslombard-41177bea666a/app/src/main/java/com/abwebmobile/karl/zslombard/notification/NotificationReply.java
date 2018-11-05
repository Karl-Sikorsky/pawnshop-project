package com.abwebmobile.karl.zslombard.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abwebmobile.karl.zslombard.R;

public class NotificationReply extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_reply);
findViewById(R.id.buttonNotification2).setOnClickListener(this);
        findViewById(R.id.buttonNotification3).setOnClickListener(this);
        findViewById(R.id.buttonNotification4).setOnClickListener(this);
        findViewById(R.id.buttonNotification5).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "Вашу відповідь прийнято, дякуємо", Toast.LENGTH_SHORT).show();
    }
}
