package com.ah.dagbow.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ah.dagbow.MainActivity;
import com.ah.dagbow.R;
import com.google.firebase.crash.FirebaseCrash;

public class MainFragment extends Fragment {

    private final String TAG = MainFragment.class.getSimpleName();
    //public ProgressDialog progress;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseCrash.log("Activity created");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.main, container, false);

        /* New Journey! Button */
        final Button newGameBtn = (Button) v.findViewById(R.id.new_button);
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "New Button");
                ((MainActivity)getActivity()).newStoryFunc();
            }
        });

        /* Continue button */
        final Button contBtn = (Button) v.findViewById(R.id.cont_button);
        if(((MainActivity) getActivity()).PlayerChoices.size() == 0){
            contBtn.setAlpha(.5f);
            contBtn.setClickable(false);
            contBtn.setEnabled(false);
        }
        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Continue Button");
                ((MainActivity)getActivity()).continueFunc();
            }
        });

        /* Journey Overview Button */
        final Button overviewBtn = (Button) v.findViewById(R.id.overview_button);
        if(((MainActivity) getActivity()).PlayerChoices.size() == 0){
            contBtn.setAlpha(.5f);
            contBtn.setClickable(false);
            contBtn.setEnabled(false);
        }
        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Continue Button");
                ((MainActivity)getActivity()).overviewFunc();
            }
        });

        /* Day/Night Mode Button */
        final Button dayNightBtn = (Button) v.findViewById(R.id.day_night_button);
        dayNightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Day mode");
                ((MainActivity)getActivity()).changeTheme();
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