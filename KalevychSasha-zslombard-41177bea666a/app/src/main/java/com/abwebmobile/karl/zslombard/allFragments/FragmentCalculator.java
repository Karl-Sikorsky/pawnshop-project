package com.abwebmobile.karl.zslombard.allFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.abwebmobile.karl.zslombard.Calculable;
import com.abwebmobile.karl.zslombard.CircularTextView;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.CalculatorPresenter;

/**
 * Created by Karl on 03.02.2018.
 * Фрагмент "КАЛЬКУЛЯТОР КРЕДИТА
 */

public class FragmentCalculator extends Fragment implements View.OnClickListener, Calculable {
    CircularTextView[] mTextViews;
    CalculatorPresenter mCalculatorPresenter;
    TextView summary, percentUsage, percentAbs, toReturnTV ;
    Spinner spinnerContent;
    ArrayAdapter<CharSequence> spinnerAdapter;
    EditText editWeight;
    private Button[] btn = new Button[4];
    private Button btn_unfocus;
    private int[] btn_id = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3};
    ScrollView scroll;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mCalculatorPresenter = new CalculatorPresenter(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);
        scroll = (ScrollView)v.findViewById(R.id.scrollView);
        //это для того, чтобы устранить постоянное цепляние фокуса на EditText автоматически
        scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (editWeight.hasFocus()) {
                    editWeight.clearFocus();
                }
                return false;
            }
        });

        toReturnTV = (TextView)v.findViewById(R.id.textViewToReturn);
        percentAbs = (TextView)v.findViewById(R.id.textViewPercentAbs);
        percentUsage = (TextView)v.findViewById(R.id.textViewPercentUsage);
        summary = (TextView)v.findViewById(R.id.textViewSum);

        RadioGroup radioGroup = (RadioGroup)v.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton) {
                    mCalculatorPresenter.setSilverGold(0);
                    spinnerAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.goldContentsList, R.layout.spinner_item);
                    spinnerContent.setAdapter(spinnerAdapter);
                    spinnerContent.setSelection(0);
                    Log.d("calculator", "collect silverGold = " + (0));
                }
                if (i==R.id.radioButton2){
                    mCalculatorPresenter.setSilverGold(1);
                    spinnerAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.silverContentList, R.layout.spinner_item);
                    spinnerContent.setAdapter(spinnerAdapter);
                    spinnerContent.setSelection(0);
                    Log.d("calculator", "collect silverGold = " + (1));
                }
            }
        });

        Spinner spinnerCity = (Spinner)v.findViewById(R.id.spinnerCity);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCalculatorPresenter.setCity(i);
                Log.d("calculator", "collect city = "+(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Spinner spinnerDepartment = (Spinner)v.findViewById(R.id.spinnerDepartment);
        spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCalculatorPresenter.setCityAdress(i);
                Log.d("calculator", "collect dept = "+(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinnerContent = (Spinner)v.findViewById(R.id.spinnerContent);
        spinnerContent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCalculatorPresenter.setContent(i);
                Log.d("calculator", "collect content = "+(i+1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

      editWeight = (EditText)v.findViewById(R.id.editTextWeight);
        editWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    mCalculatorPresenter.setWeight(Float.valueOf(editable.toString()));
                }catch (NumberFormatException e){
                    //Sorry for this, that going if entered weight yet not valid
                }
                Log.d("calculator", "collect weight = "+editable.toString());
            }
        });

        mTextViews = new CircularTextView[35];
        for (int i=0;i<mTextViews.length;i++){
            String s = "circularTextView"+(i+1);
            int resID = getResources().getIdentifier(s, "id", getActivity().getPackageName());
            mTextViews[i] = ((CircularTextView)v.findViewById(resID));
            mTextViews[i].setStrokeWidth(1);
            mTextViews[i].setStrokeColor("#ffffff");
            mTextViews[i].setSolidColor("#99777777");
            final int finalI = i;
            mTextViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (CircularTextView mTextView : mTextViews) {
                        mTextView.setSolidColor("#99777777");
                    }
                    mTextViews[finalI].setSolidColor("#FEE36B");
                    mCalculatorPresenter.setDays(finalI);
                    Log.d("calculator", "collect days = "+finalI);
                }
            });
        }

        for(int i = 0; i < btn.length; i++){
            btn[i] = (Button)v.findViewById(btn_id[i]);
            btn[i].setBackgroundColor(Color.rgb(207, 207, 207));
            btn[i].setOnClickListener(this);
        }

        btn_unfocus = btn[0];

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn0 :
                setFocus(btn_unfocus, btn[0], 0);
                break;

            case R.id.btn1 :
                setFocus(btn_unfocus, btn[1], 1);
                break;

            case R.id.btn2 :
                setFocus(btn_unfocus, btn[2], 2);
                break;

            case R.id.btn3 :
                setFocus(btn_unfocus, btn[3], 3);
                break;
        }
    }

    private void setFocus(Button btn_unfocus, Button btn_focus, int btnId) {
        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackgroundColor(Color.rgb(207, 207, 207));
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundColor(Color.rgb(191, 90, 0));
        mCalculatorPresenter.setStatus(btnId);
        Log.d("calculator", "collect Status = "+btnId);
        this.btn_unfocus = btn_focus;
    }

    @Override
    public void onCalculate(String sumCredit, String absPercent, String usagePercent, String toReturn) {
        Log.d("calculator", "in fragment onCalculate ");
        summary.setText(sumCredit);
        percentAbs.setText(absPercent+"%");
        percentUsage.setText(usagePercent);
        toReturnTV.setText(toReturn);

    }
}
