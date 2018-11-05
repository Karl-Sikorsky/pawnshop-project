package com.abwebmobile.karl.zslombard.allFragments;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RoomEntities.Offer;

import com.abwebmobile.karl.zslombard.useCasesAndIteractors.Callbacker;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.InfoViewModel;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.List;

import static com.abwebmobile.karl.zslombard.Constants.OFFERS_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.QREADER_FRAGMENT;

/**
 * Created by Karl on 07.02.2018.
 * Фрагмент, отвечающий за процесс логина пользователя
 */

public class LoginFragment extends Fragment implements Callbacker{
    HostView mHostView;
    String mOfferID;
    InfoViewModel mViewModel;
    EditText login, password, edit;
    private TextInputLayout inputLayoutName, inputLayoutOffer, inputLayoutPassword;
    TextView progress;
    DilatingDotsProgressBar dilatingDotsProgressBar;

    public void setHostView(HostView hostView) {
        this.mHostView = hostView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        if(mOfferID==null)mOfferID="";
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        v.findViewById(R.id.buttonScanQR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHostView.requestFragment(QREADER_FRAGMENT, "");
            }
        });
        progress = (TextView)v.findViewById(R.id.textViewProgress);
        dilatingDotsProgressBar  = (DilatingDotsProgressBar)v.findViewById(R.id.progress);
        inputLayoutName = (TextInputLayout) v.findViewById(R.id.linearUserLogin);
        inputLayoutOffer = (TextInputLayout) v.findViewById(R.id.linearOffer);
        inputLayoutPassword = (TextInputLayout) v.findViewById(R.id.linearUserPass);

        Button buttonLogin = (Button)v.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
              if (submitForm()) {
                  getOffers();
              }
            }
        });

        edit = (EditText) v.findViewById(R.id.editTextOfferNumber);
        edit.setText(mOfferID);
        login = (EditText)v.findViewById(R.id.editTextUserLogin);
        password = (EditText)v.findViewById(R.id.editTextUserPassword);
        edit.addTextChangedListener(new MyTextWatcher(edit));
        login.addTextChangedListener(new MyTextWatcher(login));
        password.addTextChangedListener(new MyTextWatcher(password));
        Log.d("qrlog", "edit text setted for "+mOfferID);
        return v;
    }

    private boolean submitForm() {
        if (!validateLogin()) {
            return false;
        }
        if (!validateOffer()) {
            return false;
        }
        if (!validatePassword()) {
            return false;
        }
        return  true;
    }


    private boolean validateLogin() {
        if (login.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.hint_login));
            requestFocus(login);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
            mViewModel.setCurrentUserLogin(login.getText().toString().trim());
        }

        return true;

    }
    private boolean validateOffer() {
        if ((edit.getText().toString().trim().length()<11)||((edit.getText().toString().trim().length()>11))) {
            inputLayoutOffer.setError(getString(R.string.hint_offer_number));
            requestFocus(edit);
            return false;
        } else {
            mViewModel.setCurrentUserOfferNumber((edit.getText().toString().trim()));
            inputLayoutOffer.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.hint_password));
            requestFocus(password);
            return false;
        } else {
            mViewModel.setCurrentUserPassword((password.getText().toString().trim()));
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private void hideLoading() {
        progress.setVisibility(View.GONE);
        dilatingDotsProgressBar.hideNow();
    }

    private void showLoading() {
        progress.setVisibility(View.VISIBLE);
        dilatingDotsProgressBar.showNow();
    }

    public void setOfferID(String offerID) {
        if (offerID!=null) {
            this.mOfferID = offerID;
            Log.d("qrlog", "value setted into " + this.mOfferID);
        }
    }

    @Override
    public void onCallback(String command) {
        hideLoading();
        Toast.makeText(getActivity().getApplicationContext(), R.string.unsucsessful_login, Toast.LENGTH_SHORT).show();
    }

    public void getOffers() {
        final LiveData<List<Offer>> offers = mViewModel.getOffersByCurrentUser(this);
        if (offers.getValue() != null) {
            Log.d("sell", "after getItems called size = " + offers.getValue().size());
        }
        showLoading();
        offers.observe(getActivity(), new Observer<List<Offer>>() {
            @Override
            public void onChanged(@Nullable List<Offer> items) {

                Log.d("sell", "ItemsChanged size = " + items.size());
                if (items.size()>0){
                       hideLoading();
                    mViewModel.addLoginedUserOffers(items);
                    mHostView.requestFragment(OFFERS_FRAGMENT, "");
                }

            }
        });
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.editTextUserLogin:
                    validateLogin();
                    break;
                case R.id.editTextOfferNumber:
                    validateOffer();
                    break;
                case R.id.editTextUserPassword:
                    validatePassword();
                    break;
            }
        }
    }


}


