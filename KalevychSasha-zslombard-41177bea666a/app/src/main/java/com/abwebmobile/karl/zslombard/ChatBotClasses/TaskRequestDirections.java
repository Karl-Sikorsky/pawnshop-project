package com.abwebmobile.karl.zslombard.ChatBotClasses;

import android.os.AsyncTask;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Karl on 02.03.2018.
 */
public class TaskRequestDirections extends AsyncTask<String, Void, String> {
TaskRequestCallback taskRequestCallback;
    public TaskRequestDirections(TaskRequestCallback taskRequestCallback) {
        this.taskRequestCallback = taskRequestCallback;
    }

    @Override
    protected String doInBackground(String... strings) {
        String responseString = "";
        try {
            responseString = requestDirection(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  responseString;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Parse json here
        TaskParser taskParser = new TaskParser(taskRequestCallback);
        taskParser.execute(s);
    }

    private String requestDirection(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try{
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            //Get the response result
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

}
