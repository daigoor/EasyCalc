package edu.ibda.training.android.activities.impl;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

import edu.ibda.training.android.R;
import edu.ibda.training.android.activities.AbstractActivity;
import edu.ibda.training.android.adapters.HistoryAdaptter;
import edu.ibda.training.android.beans.Raw;
import edu.ibda.training.android.database.DatabaseSources;
import edu.ibda.training.android.database.SharedPrefManager;
import edu.ibda.training.android.utils.Constants;
import edu.ibda.training.android.utils.Utils;

import static android.R.id.content;

/**
 * Created by DaiGooR on 9/9/2017.
 */

public class HistoryActivity extends AbstractActivity {

    private static final String TAG = "HistoryActivity";

    private DatabaseSources ds;

//    private LinearLayout content;

    private ListView historyList;

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        setIntent(new Intent());
        super.onBackPressed();
    }

    @Override
    protected void init() {
//        content = (LinearLayout) findViewById(R.id.ll_content);
//
//        try {
//            JSONArray array = SharedPrefManager.getInstance(this).getHistory();
//            Raw raw = null;
//            for(int index = 0; index < array.length(); index++) {
//                raw = new Gson().fromJson( array.getJSONObject(index).toString(), Raw.class);
//                LayoutInflater mInflater = (LayoutInflater)this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
//                View v = mInflater.inflate(R.layout.raw, null, false);
//                TextView tv = (TextView) v.findViewById(R.id.tv_first_value);
//                tv.setText(raw.getFirstValue() + "");
//
//
//                content.addView(v);
//            }
//        } catch (JSONException e) {
//            Log.e(TAG, "there was an big exception -_-!!!");
//            showMessage("OPS!");
//        }

        historyList = (ListView) findViewById(R.id.lv_history);

        ds = new DatabaseSources(this);
        ds.open();
        final List<Raw> raws = ds.getAllHistory();
        ds.close();
        final HistoryAdaptter adapter = new HistoryAdaptter(this, 0, raws);
        historyList.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                raws.add(new Raw(1, 1, Constants.OPERATIONS.add));
                adapter.notifyDataSetChanged();
                showMessage("done");
                return false;
            }
        });
        historyList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                raws.add(new Raw(1, 1, Constants.OPERATIONS.add));
                adapter.notifyDataSetChanged();
                showMessage("done : " + position);
                return false;
            }
        });
        historyList.setAdapter(adapter);

//        for(Raw raw : raws) {
//            LayoutInflater mInflater = (LayoutInflater)this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
//            View v = mInflater.inflate(R.layout.raw, null, false);
//            ((TextView) v.findViewById(R.id.tv_first_value)).setText(Double.toString(raw.getFirstValue()));
//            ((TextView) v.findViewById(R.id.tv_second_value)).setText(Double.toString(raw.getSecondValue()));
//            ((TextView) v.findViewById(R.id.tv_operation)).setText(Utils.getOperationDisplayForm(raw.getSelectedOperation(), this));
//            content.addView(v);
//        }

    }

    @Override
    protected int loadLayout() {
        return R.layout.history_layout;
    }
}
