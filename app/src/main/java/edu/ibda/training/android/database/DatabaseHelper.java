package edu.ibda.training.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.ibda.training.android.utils.Constants;

import static android.content.ContentValues.TAG;

/**
 * Created by DaiGooR on 9/9/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String CREATE_HISTORY = "create table "
            + Constants.DATABASE.TABLE_HISTORY + "( " +
            Constants.DATABASE.HISTORY_COL._id.toString() + " integer primary key autoincrement, " +
            Constants.DATABASE.HISTORY_COL.first.toString() + " text not null, "+
            Constants.DATABASE.HISTORY_COL.second.toString() + " text not null," +
            Constants.DATABASE.HISTORY_COL.operation.toString() + " text not null);";

    private String DROP_HISTORY = "DROP TABLE IF EXISTS " + Constants.DATABASE.TABLE_HISTORY;

    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE.DATABASE_NAME, null, Constants.DATABASE.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,"Upgrading database from version " +
                oldVersion +
                " to " +
                newVersion +
                ", which will destroy all old data");
        db.execSQL(DROP_HISTORY);
        onCreate(db);
    }
}
