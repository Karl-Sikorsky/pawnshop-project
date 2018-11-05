package com.abwebmobile.karl.zslombard.allFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RoomEntities.SellItem;

/**
 * Created by Karl on 07.02.2018.
 */
// це для модуля торгів, поки що не використовується
public class AboutItemDialogFragment extends DialogFragment implements View.OnClickListener {
    SellItem mCurrentSellItem;
    final String LOG_TAG = "myLogs";

    public void setCurrentSellItem(SellItem currentSellItem) {
        this.mCurrentSellItem = currentSellItem;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        getDialog().setTitle("Товар №ХХХХХ");
        View v = inflater.inflate(R.layout.dialog_about_item, null);
        v.findViewById(R.id.buttonCallSeller).setOnClickListener(this);
        TextView itemName, itemPrice, ownerName, ownerPhone;
        TextView itemDescription;
        ImageView itemPic1, itemPic2, itemPic3;

        itemName = (TextView) v.findViewById(R.id.textViewItemName);
        itemDescription = (TextView) v.findViewById(R.id.textViewItemDescription);
        itemPic1 = (ImageView) v.findViewById(R.id.imageViewPic1);
        itemPic2 = (ImageView) v.findViewById(R.id.imageViewPic2);
        itemPic3 = (ImageView) v.findViewById(R.id.imageViewPic3);
        itemPrice = (TextView)  v.findViewById(R.id.textViewItemPrice);
        ownerName = (TextView) v.findViewById(R.id.textViewOwnerName);
        ownerPhone = (TextView) v.findViewById(R.id.textViewOwnerPhone);

        itemName.setText(mCurrentSellItem.name);
        itemDescription.setText(mCurrentSellItem.description);
        itemPrice.setText(mCurrentSellItem.price_presentation);

        itemPic1.setImageDrawable(resize(getResources().getDrawable(R.drawable.phone)));
        itemPic2.setImageDrawable(resize(getResources().getDrawable(R.drawable.phone2)));
        itemPic3.setImageDrawable(resize(getResources().getDrawable(R.drawable.phone3)));

        //TODO: set drawables on imageView
        ownerName.setText(mCurrentSellItem.city);
        ownerPhone.setText(mCurrentSellItem.adress);

        return v;
    }

    public void onClick(View v) {

        dismiss();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }

    private Drawable resize(Drawable image) {

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, display.getWidth(), display.getHeight()/3, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }
}