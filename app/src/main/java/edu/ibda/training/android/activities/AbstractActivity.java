package edu.ibda.training.android.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by DaiGooR on 9/9/2017.
 */

public abstract class AbstractActivity extends AppCompatActivity{

    protected abstract void init ();
    protected abstract int loadLayout ();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(loadLayout());
        init();

    }

    protected void showMessage (String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
