package edu.ibda.training.android.activities.impl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import edu.ibda.training.android.R;
import edu.ibda.training.android.activities.AbstractActivity;
import edu.ibda.training.android.adapters.RecHistoryAdapter;
import edu.ibda.training.android.beans.Raw;
import edu.ibda.training.android.database.DatabaseSources;

public class RecHistoryActivity  extends AbstractActivity {

    private RecyclerView view;

    private DatabaseSources ds;
    private List<Raw> raws;

    @Override
    protected void init() {
        view = (RecyclerView) findViewById(R.id.rec_history);

        ds = new DatabaseSources(this);
        ds.open();
        raws = ds.getAllHistory();
        ds.close();

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        view.setLayoutManager(mLayoutManager);

        RecHistoryAdapter reAdapter = new RecHistoryAdapter(this.raws);
        view.setAdapter(reAdapter);
    }

    @Override
    protected int loadLayout() {
        return R.layout.rec_view_layout;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
