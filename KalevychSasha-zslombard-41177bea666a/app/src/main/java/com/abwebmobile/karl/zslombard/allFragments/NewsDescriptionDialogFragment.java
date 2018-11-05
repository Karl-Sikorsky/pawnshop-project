package com.abwebmobile.karl.zslombard.allFragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abwebmobile.karl.zslombard.R;

/**
 * Created by Karl on 19.02.2018.
 * Диалог, отражающий описание новости
 */

public class NewsDescriptionDialogFragment extends DialogFragment {
    TextView descriptionTextView;
    String description;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        getDialog().setTitle("ЗАЛИШИТИ ЗАЯВКУ");
        View v = inflater.inflate(R.layout.dialog_fragment_news_item, null);
        descriptionTextView = (TextView)v.findViewById(R.id.news_description);
        if (description!=null)descriptionTextView.setText(description);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    public void setDescription(String description) {
       this.description = description;
    }
}
