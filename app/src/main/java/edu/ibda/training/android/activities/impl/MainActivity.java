package edu.ibda.training.android.activities.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONArray;

import java.util.Arrays;

import edu.ibda.training.android.R;
import edu.ibda.training.android.activities.AbstractActivity;
import edu.ibda.training.android.beans.Raw;
import edu.ibda.training.android.database.DatabaseSources;
import edu.ibda.training.android.database.SharedPrefManager;
import edu.ibda.training.android.therads.HttpAsnycTaskRequester;
import edu.ibda.training.android.therads.HttpThreadRequester;
import edu.ibda.training.android.utils.Constants;
import edu.ibda.training.android.utils.Utils;

public class MainActivity extends AbstractActivity implements
        View.OnClickListener, RadioGroup.OnCheckedChangeListener/* , CompoundButton.OnCheckedChangeListener */{

    private static final String TAG = "MainActivity";

    private Button result, history;
    private EditText firstNumber, secondNumber;
    private RadioGroup radioBtnGroup;
    private RadioButton add, sub, mul, div;

    private Constants.OPERATIONS selectedOp;

    private DatabaseSources ds;

    @Override
    protected void init () {
        if (SharedPrefManager.getInstance(this).isLoaded()) {
            loadViews();
            setListeners();
        } else {
            Intent intent = new Intent(this, LoadingActivity.class);
            startActivity(intent);
            this.finish();
        }

    }

    @Override
    protected int loadLayout() {
        return R.layout.main_layout;
    }

    private void loadViews () {
        this.result = (Button) findViewById(R.id.btn_result);
        this.history = (Button) findViewById(R.id.btn_history);
        this.firstNumber = (EditText) findViewById(R.id.etxt_first_number);
        this.secondNumber = (EditText) findViewById(R.id.etxt_second_number);
        this.radioBtnGroup = (RadioGroup) findViewById(R.id.rbtn_group);
        this.add = (RadioButton) findViewById(R.id.rbtn_add);
        this.add.setChecked(true);
        this.selectedOp = Constants.OPERATIONS.add;
        this.sub = (RadioButton) findViewById(R.id.rbtn_sub);
        this.mul = (RadioButton) findViewById(R.id.rbtn_mul);
        this.div = (RadioButton) findViewById(R.id.rbtn_div);
    }

    private void setListeners() {
        this.result.setOnClickListener(this);
        this.history.setOnClickListener(this);
        this.radioBtnGroup.setOnCheckedChangeListener(this);
    }

    private void CalcResult (Constants.OPERATIONS operation) throws Exception{

        double result = 0;
        double firstVal = getDoubleValueFromEditText(firstNumber);
        double secondVal = getDoubleValueFromEditText(secondNumber);

        if(operation == Constants.OPERATIONS.add) {
            result =  firstVal + secondVal ;

        } else if (operation == Constants.OPERATIONS.sub) {
            result =  firstVal - secondVal ;

        } else if (operation == Constants.OPERATIONS.mul) {
            result = firstVal * secondVal ;

        } else if (operation == Constants.OPERATIONS.div) {
            result =  firstVal / secondVal ;

        }

        SharedPrefManager.getInstance(this).setHistory(new Raw(firstVal, secondVal, operation));

        ds = new DatabaseSources(this);
        ds.open();
        ds.addToHistory(new Raw(firstVal, secondVal, operation));
        ds.close();

        showMessage("Operation : " +firstVal + " " + Utils.getOperationDisplayForm(operation, this) + " " + secondVal + " = " + result);
    }

    private double getDoubleValueFromEditText (EditText etxt) throws Exception{
        try {
            String value = etxt.getText().toString();
            if(value != null && value.length() > 0) {
                return Double.parseDouble(value);
            }
            return 0;
        } catch (Exception e) {
            JSONArray jsonArray = new JSONArray(Arrays.asList(e.getStackTrace()));
            Log.e(TAG, "There was an error: " + e.getMessage() + " ,stack: " + jsonArray.toString());
            throw e;
        }
    }

    private void showHistory () {
        Intent intent = new Intent(this, RecHistoryActivity.class);
        Bundle budle = new Bundle();
        budle.putString("key", "value");
        intent.putExtras(budle);
        intent.putExtra("ekey", "evalue");
        startActivity(intent);
        startActivityForResult(intent , 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10) {
            if(requestCode == RESULT_OK) {
                String dxx = data.getStringExtra("blabla");
            }
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_history:
                    showHistory();
                    break;
                case R.id.btn_result:
                    CalcResult(this.selectedOp);
                    break;
            }

        } catch (Exception e) {
            Log.e(TAG, "Could not complete op. Please check logs");
            showMessage("Error detected!");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        //        RadioButton changedRBtn = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        switch (group.getId()) {
            case R.id.rbtn_group :
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.rbtn_add :
                        this.selectedOp = Constants.OPERATIONS.add;
                        break;
                    case R.id.rbtn_sub :
                        this.selectedOp = Constants.OPERATIONS.sub;
                        break;
                    case R.id.rbtn_mul :
                        this.selectedOp = Constants.OPERATIONS.mul;
                        break;
                    case R.id.rbtn_div :
                        this.selectedOp = Constants.OPERATIONS.div;
                        break;
                }
                break;
        }
        Log.i(TAG, "User selected an operation , selected operation:" + this.selectedOp.toString());
    }

//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        Toast.makeText(this, "v.id : " + buttonView.getId(), Toast.LENGTH_SHORT).show();
//        switch (buttonView.getId()) {
//            case R.id.rbtn_add :
//                break;
//            case R.id.rbtn_sub :
//                break;
//            case R.id.rbtn_mul :
//                break;
//            case R.id.rbtn_div :
//                break;
//        }
//    }


}
