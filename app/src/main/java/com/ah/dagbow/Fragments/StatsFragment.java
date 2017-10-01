package com.ah.dagbow.Fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ah.dagbow.R;

public class StatsFragment extends Fragment {

    private final String TAG = StatsFragment.class.getSimpleName();
    //public ProgressDialog progress;

    Handler handler = new Handler();

    public StatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.stats, container, false);

        LinearLayout linearLayout = v.findViewById(R.id.linearLayoutStats);

        TextView stat = new TextView(getActivity());
        stat.setText("Name:");
        stat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        stat.setTypeface(Typeface.DEFAULT_BOLD);
        linearLayout.addView(stat);

        Button backBtn = v.findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "stats back button Pressed");
                (getActivity()).onBackPressed();
            }
        });

        return v;
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop()");
        super.onStop();
    }
}