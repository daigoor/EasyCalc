package edu.ibda.training.android;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, RadioGroup.OnCheckedChangeListener/* , CompoundButton.OnCheckedChangeListener */{

    private static final String TAG = "MainActivity";

    private enum operation {
        add, sub, mul, div
    }

    private Button result;
    private EditText firstNumber, secondNumber;
    private RadioGroup radioBtnGroup;
    private RadioButton add, sub, mul, div;



    private operation selectedOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(R.layout.activity_main);
    }

    private void init (int layout) {
        setContentView(layout);
        loadViews();
        setListeners();
    }

    private void loadViews () {
        this.result = (Button) findViewById(R.id.btn_result);
        this.firstNumber = (EditText) findViewById(R.id.etxt_first_number);
        this.secondNumber = (EditText) findViewById(R.id.etxt_second_number);
        this.radioBtnGroup = (RadioGroup) findViewById(R.id.rbtn_group);
        this.add = (RadioButton) findViewById(R.id.rbtn_add);
        this.add.setChecked(true);
        this.selectedOp = operation.add;
        this.sub = (RadioButton) findViewById(R.id.rbtn_sub);
        this.mul = (RadioButton) findViewById(R.id.rbtn_mul);
        this.div = (RadioButton) findViewById(R.id.rbtn_div);
    }

    private void setListeners() {
        this.result.setOnClickListener(this);
        this.radioBtnGroup.setOnCheckedChangeListener(this);
    }

    private void CalcResult (operation op) throws Exception{

        double result = 0;
        double firstVal = getDoubleValueFromEditText(firstNumber);
        double secondVal = getDoubleValueFromEditText(secondNumber);
        String opStr = null;
        if(op == operation.add) {
            int x = 0;
            int y = 1;

            int r = y / x;

            result =  firstVal + secondVal ;
            opStr = getString(R.string.rbtn_add);
        } else if (op == operation.sub) {
            result =  firstVal - secondVal ;
            opStr = getString(R.string.rbtn_sub);
        } else if (op == operation.mul) {
            result = firstVal * secondVal ;
            opStr = getString(R.string.rbtn_mul);
        } else if (op == operation.div) {
            result =  firstVal / secondVal ;
            opStr = getString(R.string.rbtn_div);
        }

        showMessage("Operation : " +firstVal + " " + opStr + " " + secondVal + " = " + result);
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
    private void showMessage (String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        try {
            CalcResult(this.selectedOp);
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
                        this.selectedOp = operation.add;
                        break;
                    case R.id.rbtn_sub :
                        this.selectedOp = operation.sub;
                        break;
                    case R.id.rbtn_mul :
                        this.selectedOp = operation.mul;
                        break;
                    case R.id.rbtn_div :
                        this.selectedOp = operation.div;
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
