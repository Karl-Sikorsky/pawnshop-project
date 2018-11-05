package com.abwebmobile.karl.zslombard.qrStaff;

import android.text.TextUtils;

/**
 * Created by Karl on 08.02.2018.
 */

public class Validator {
    public Validator() {
    }

    public boolean isPhoneValid(String phone){
        boolean valid = false;
        if((phone.matches("[-+]?\\d+"))&&(phone.trim().length()==12)&&(phone.startsWith("380")))valid=true;
        return valid;
    }
    public boolean isMailValid(CharSequence target){
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
