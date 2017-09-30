package edu.ibda.training.android.Fragements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.ibda.training.android.R;

/**
 * Created by DaiGooR on 9/30/2017.
 */

public class PagerFragment extends Fragment {

    public static Fragment getInstance (String param) {
        PagerFragment pFragment = new PagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("param" , param);
        pFragment.setArguments(bundle);
        return pFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, container, false);
        ((TextView)view.findViewById(R.id.txt_name)).setText(getArguments().getString("param"));
        return view;
    }
}
