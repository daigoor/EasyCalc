package edu.ibda.training.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.ibda.training.android.R;
import edu.ibda.training.android.beans.Raw;

/**
 * Created by DaiGooR on 9/16/2017.
 */

public class RecHistoryAdapter extends RecyclerView.Adapter<RecHistoryAdapter.CustomHolder>{

    private List<Raw> items;

    public RecHistoryAdapter(List<Raw> items) {
        this.items = items;
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.card_raw, parent, false);
        return new CustomHolder(rowView);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        Raw raw = this.items.get(position);
        holder.firstValue.setText(Double.toString(raw.getFirstValue()));
        holder.operation.setText(raw.getSelectedOperation().toString());
        holder.secondValue.setText(Double.toString(raw.getSecondValue()));
    }

    class CustomHolder extends RecyclerView.ViewHolder {
        public TextView firstValue, operation, secondValue;
        public CustomHolder(View itemView) {
            super(itemView);
            firstValue = (TextView) itemView.findViewById(R.id.tv_first_value);
            operation = (TextView) itemView.findViewById(R.id.tv_first_value);
            secondValue = (TextView) itemView.findViewById(R.id.tv_first_value);
        }
    }
}
