package edu.ibda.training.android.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import edu.ibda.training.android.R;
import edu.ibda.training.android.beans.Raw;
import edu.ibda.training.android.utils.Utils;

/**
 * Created by DaiGooR on 9/9/2017.
 */

public class HistoryAdaptter extends ArrayAdapter<Raw> {

    private List<Raw> raws;
    private Context context;

    public HistoryAdaptter(@NonNull Context context, @LayoutRes int resource, List<Raw> raws) {
        super(context, resource);
        this.raws = raws;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Raw raw = raws.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.raw, parent, false);
        ((TextView) rowView.findViewById(R.id.tv_first_value)).setText(Double.toString(raw.getFirstValue()));
        ((TextView) rowView.findViewById(R.id.tv_second_value)).setText(Double.toString(raw.getSecondValue()));
        ((TextView) rowView.findViewById(R.id.tv_operation)).setText(Utils.getOperationDisplayForm(raw.getSelectedOperation(), this.context));
        return rowView;
    }

    @Override
    public int getCount() {
        return raws.size();
    }

}
