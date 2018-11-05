package com.abwebmobile.karl.zslombard.allFragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.abwebmobile.karl.zslombard.R;

/**
 * Created by Karl on 11.03.2018.
 */

public class DialogAddCard extends DialogFragment {
    EditText editCard1, editCard2;
    private TextInputLayout inputLayoutCard1, inputLayoutCard2;
Button apply;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        getDialog().setTitle("ЗАКРІПИТИ КАРТКУ");
        View v = inflater.inflate(R.layout.dialog_add_card, container, false);


        inputLayoutCard1 = (TextInputLayout) v.findViewById(R.id.linearCard1);
        inputLayoutCard2 = (TextInputLayout) v.findViewById(R.id.linearCard2);

        editCard1 = (EditText)v.findViewById(R.id.editTextCardNumber1);
        editCard2 = (EditText)v.findViewById(R.id.editTextCardNumber2) ;

        editCard1.addTextChangedListener(new MyTextWatcher(editCard1));
        editCard2.addTextChangedListener(new MyTextWatcher(editCard2));

        apply = (Button)v.findViewById(R.id.buttonAddCard);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (submitForm()) {
                    sendCard();
                }
            }
        });

        return v;
    }

    private boolean submitForm() {
        if (!validateCard1()) {
            return false;
        }
        if (!validateCard2()) {
            return false;
        }
        return true;
    }

    private boolean validateCard2() {
        if (!(editCard2.getText().toString().trim().equals(editCard1.getText().toString().trim()))) {
        inputLayoutCard2.setError(getString(R.string.hint_error_card2));
        requestFocus(editCard2);
        return false;
    } else {
        inputLayoutCard2.setErrorEnabled(false);

    }

        return true;
    }

    private boolean validateCard1() {
        if (editCard1.getText().toString().trim().length()!=16) {
        inputLayoutCard1.setError(getString(R.string.hint_error_card1));
        requestFocus(editCard1);
        return false;
    } else {
        inputLayoutCard1.setErrorEnabled(false);

        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void sendCard() {
        // send POST with users card data to server
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
                case R.id.editTextCardNumber1:
                    validateCard1();
                    break;
                case R.id.editTextCardNumber2:
                    validateCard2();
                    break;

            }
        }
    }
}
