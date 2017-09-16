package edu.ibda.training.android.beans;

import android.content.Context;

import edu.ibda.training.android.utils.Constants;
import edu.ibda.training.android.utils.Utils;

/**
 * Created by DaiGooR on 9/9/2017.
 */

public class Raw {

    private long id;
    private double firstValue;
    private double secondValue;
    private Constants.OPERATIONS selectedOperation;

    public Raw(long id, double firstValue, double secondValue, Constants.OPERATIONS selectedOperation) {
        this.id = id;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.selectedOperation = selectedOperation;
    }

    public Raw(double firstValue, double secondValue, Constants.OPERATIONS selectedOperation) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.selectedOperation = selectedOperation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(double firstValue) {
        this.firstValue = firstValue;
    }

    public double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(double secondValue) {
        this.secondValue = secondValue;
    }

    public Constants.OPERATIONS getSelectedOperation() {
        return selectedOperation;
    }

    public void setSelectedOperation(Constants.OPERATIONS selectedOperation) {
        this.selectedOperation = selectedOperation;
    }

    @Override
    public String toString() {
        return getFirstValue() + " " + getSelectedOperation() + " " + getSecondValue();
    }

    public String toString(Context cntx) {
        return getFirstValue() + " " + Utils.getOperationDisplayForm(getSelectedOperation(), cntx) + " " + getSecondValue();
    }
}
