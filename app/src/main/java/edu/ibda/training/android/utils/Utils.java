package edu.ibda.training.android.utils;

import android.content.Context;

import edu.ibda.training.android.R;

/**
 * Created by DaiGooR on 9/9/2017.
 */

public class Utils {

    public static String getOperationDisplayForm (Constants.OPERATIONS operation, Context cntx) {
        if(operation == Constants.OPERATIONS.add) {
            return cntx.getString(R.string.rbtn_add);
        } else if (operation == Constants.OPERATIONS.sub) {
            return cntx.getString(R.string.rbtn_sub);
        } else if (operation == Constants.OPERATIONS.mul) {
            return cntx.getString(R.string.rbtn_mul);
        } else if (operation == Constants.OPERATIONS.div) {
            return cntx.getString(R.string.rbtn_div);
        }
        return null;
    }

    public static Constants.OPERATIONS getOperation (String operation) {
        if(operation.equals(Constants.OPERATIONS.add.toString())) {
            return Constants.OPERATIONS.add;
        } else if (operation.equals(Constants.OPERATIONS.sub.toString())) {
            return Constants.OPERATIONS.sub;
        } else if (operation.equals(Constants.OPERATIONS.mul.toString())) {
            return Constants.OPERATIONS.mul;
        } else if (operation.equals(Constants.OPERATIONS.div.toString())) {
            return Constants.OPERATIONS.div;
        }
        return null;
    }
}
