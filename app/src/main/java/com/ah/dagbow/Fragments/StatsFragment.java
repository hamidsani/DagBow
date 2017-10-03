package com.ah.dagbow.Fragments;

import android.app.Fragment;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ah.dagbow.Common.Enum;
import com.ah.dagbow.MainActivity;
import com.ah.dagbow.R;

public class StatsFragment extends Fragment {

    private final String TAG = StatsFragment.class.getSimpleName();
    //public ProgressDialog progress;

    Handler handler = new Handler();
    View v;

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
        v = inflater.inflate(R.layout.stats, container, false);

        LinearLayout linearLayout = v.findViewById(R.id.linearLayoutStats);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(((MainActivity) getActivity()).padding(15), ((MainActivity) getActivity()).padding(15), 0, ((MainActivity) getActivity()).padding(15));

        for (Enum.stats statValue: Enum.stats.values()){

            TextView stat = new TextView(getActivity());
            Log.d(TAG, MainActivity.hero.getFieldName(statValue) + MainActivity.hero.getField(statValue));
            String sourceString = "<b>" + MainActivity.hero.getFieldName(statValue) + "</b> " + MainActivity.hero.getField(statValue);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                stat.setText(Html.fromHtml(sourceString,Html.FROM_HTML_MODE_LEGACY));
            } else {
                stat.setText(Html.fromHtml(sourceString));
            }

            stat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            stat.setLayoutParams(params);
            //stat.setTypeface(Typeface.DEFAULT_BOLD);
            linearLayout.addView(stat);
        }


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

    public float getXFraction()
    {
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        return (size.x == 0) ? 0 : v.getX() / (float) (size.x);
    }

    public void setXFraction(float xFraction) {
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        v.setX((size.x > 0) ? (xFraction * size.x) : 0);
    }
}