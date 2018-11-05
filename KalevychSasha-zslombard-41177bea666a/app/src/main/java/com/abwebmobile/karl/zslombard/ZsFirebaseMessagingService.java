package com.abwebmobile.karl.zslombard;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.util.Log;
import android.widget.RemoteViews;

import com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories.DatabaseProvider;
import com.abwebmobile.karl.zslombard.RoomEntities.SettingsState;
import com.abwebmobile.karl.zslombard.notification.NotificationReply;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Karl on 27.02.2018.
 * <p>
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class ZsFirebaseMessagingService extends FirebaseMessagingService {
    private static final String MyOnClick = "myOnClickTag";
    private static final String TAG = "MyFirebaseMsgService";
    private static final String KEY_TEXT_REPLY = "key_text_reply";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]
        DatabaseProvider.getInstance().createDatabaseReference(getApplicationContext());
        SettingsState sst = DatabaseProvider.getInstance().getCurrentSettingsState();
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());



        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {


            String type = remoteMessage.getData().get("type");
            if (type.equals("button_reply")&& sst.checkBoxReminderState) {
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());

                if (/* Check if data needs to be processed by long running job */ true) {
                    // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                    sendNotification(remoteMessage.getData().get("message"));
                    scheduleJob();
                } else {
                    // Handle message within 10 seconds
                    handleNow();
                }
            }else{
                if(type.equals("input_reply")){
                   sendInputNotification(remoteMessage.getData().get("message"));

                }else {


                    sendSimplestNotification(remoteMessage.getData().get("message"));
                }
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void sendInputNotification(String message) {
/*
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("reply_action");
        intent.putExtra("key_message_id", 111);
        intent.putExtra("key_notification_id", 222);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("text", message);

        PendingIntent replyPendingIntent = PendingIntent.getActivity(this, 100, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);*/



             //a
        String replyLabel = getResources().getString(R.string.reply_label);
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();


        //b
        NotificationCompat.Action compatAction =
                new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, replyLabel,
                        getReplyPendingIntent())
                        .addRemoteInput(remoteInput).
                        setAllowGeneratedReplies(true).
                        build();
         //d
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.zs_logo)
                .setContentTitle("title input")
                .setContentText("content input")
                .setShowWhen(true)
                .addAction(compatAction); // reply action from step b above

        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(this);
        mNotificationManager.notify(200, mBuilder.build());





    }

    private void sendSimplestNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("text", messageBody);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 546/* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);


        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.zs_logo)
                        .setContentTitle("НОВА АКЦІЯ")
                        .setContentText(messageBody)
                        .setAutoCancel(false)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);



        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(546 /* ID of notification */, notificationBuilder.build());

    }
    // [END receive_message]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */
    private void scheduleJob() {
        // [START dispatch_job]
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("my-job-tag")
                .build();
        dispatcher.schedule(myJob);
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, NotificationReply.class);
        intent.putExtra("text", messageBody);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 546/* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Intent intentBtn = new Intent(this, MainActivity.class);
        intentBtn.putExtra("text", messageBody);
        intentBtn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntentBtn = PendingIntent.getActivity(this, 546/* Request code */, intentBtn,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        RemoteViews remoteViews =  new RemoteViews(getPackageName(), R.layout.activity_notification_reply);
        remoteViews.setTextViewText(R.id.textViewReminder, messageBody);
        remoteViews.setTextColor(R.id.textViewReminder, Color.BLACK);
        remoteViews.setOnClickPendingIntent(R.id.buttonNotification2,
                pendingIntentBtn);
        remoteViews.setOnClickPendingIntent(R.id.buttonNotification3,
                pendingIntentBtn);
        remoteViews.setOnClickPendingIntent(R.id.buttonNotification4,
                pendingIntentBtn);
        remoteViews.setOnClickPendingIntent(R.id.buttonNotification5,
               pendingIntentBtn);
        
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.zs_logo)
                        .setContentTitle("СТРОК ДОГОВОРУ")
                        .setContentText(messageBody)
                        .setAutoCancel(false)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setCustomBigContentView(remoteViews);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(546 /* ID of notification */, notificationBuilder.build());
    }

    public PendingIntent getReplyPendingIntent() {
        Intent intent;

            // start your activity for Android M and below
            intent = new Intent(this, MainActivity.class);
            intent.setAction("reply_action");
            intent.putExtra("key_message_id", 500);
            intent.putExtra("key_notification_id", "notifyId");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return PendingIntent.getActivity(this, 100, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);


    }
}