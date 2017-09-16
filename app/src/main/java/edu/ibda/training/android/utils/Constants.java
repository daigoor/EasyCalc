package edu.ibda.training.android.utils;

/**
 * Created by DaiGooR on 9/9/2017.
 */

public interface Constants {
    String SP_NAME = "test";

    enum OPERATIONS {
        add, sub, mul, div
    }

    enum KEYS {
        FIRST_VALUE , OPERATION , SECOND_VALUE
    }

    interface DATABASE {
        int VERSION = 1;
        String DATABASE_NAME = "easy_calc.db";
        String TABLE_HISTORY = "history_table";
        enum HISTORY_COL {
            _id, first , second, operation
        }
    }

}
