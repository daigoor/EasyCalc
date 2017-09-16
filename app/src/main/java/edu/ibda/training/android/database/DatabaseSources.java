package edu.ibda.training.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.ibda.training.android.beans.Raw;
import edu.ibda.training.android.utils.Constants;
import edu.ibda.training.android.utils.Utils;

/**
 * Created by DaiGooR on 9/9/2017.
 */

public class DatabaseSources {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public DatabaseSources (Context cntx) {
        this.helper = new DatabaseHelper(cntx);
    }

    public void open () {
        this.db = this.helper.getWritableDatabase();
    }

    public void close () {
        this.db.close();
        this.helper.close();
    }

    public long addToHistory (Raw raw) {
        ContentValues values = new ContentValues();
        values.put(Constants.DATABASE.HISTORY_COL.first.toString(), Double.valueOf(raw.getFirstValue()));
        values.put(Constants.DATABASE.HISTORY_COL.second.toString(), Double.valueOf(raw.getSecondValue()));
        values.put(Constants.DATABASE.HISTORY_COL.operation.toString(), raw.getSelectedOperation().toString());
        return this.db.insert(Constants.DATABASE.TABLE_HISTORY, null, values);
    }

    public List<Raw> getAllHistory() {
        List<Raw> raws = new ArrayList<Raw>();
        String [] cols = {
                Constants.DATABASE.HISTORY_COL._id.toString(),
                Constants.DATABASE.HISTORY_COL.first.toString(),
                Constants.DATABASE.HISTORY_COL.second.toString(),
                Constants.DATABASE.HISTORY_COL.operation.toString(),
        };
        Cursor cursor = this.db.query(Constants.DATABASE.TABLE_HISTORY, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Constants.DATABASE.HISTORY_COL._id.toString()));
            String first = cursor.getString(cursor.getColumnIndex(Constants.DATABASE.HISTORY_COL.first.toString()));
            String second = cursor.getString(cursor.getColumnIndex(Constants.DATABASE.HISTORY_COL.second.toString()));
            String operation = cursor.getString(cursor.getColumnIndex(Constants.DATABASE.HISTORY_COL.operation.toString()));
            raws.add(new Raw(id, Double.parseDouble(first), Double.parseDouble(second), Utils.getOperation(operation)));
            cursor.moveToNext();
        }
        return raws;
    }


}
