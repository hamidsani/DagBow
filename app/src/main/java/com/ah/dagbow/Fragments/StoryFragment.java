package com.ah.dagbow.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ah.dagbow.Common.Choice;
import com.ah.dagbow.MainActivity;
import com.ah.dagbow.R;

public class StoryFragment extends Fragment {

    private final String TAG = StoryFragment.class.getSimpleName();
    public LinearLayout linearLayout;
    //public ProgressDialog progress;

    public StoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.story, container, false);

        MainActivity mainAct = (MainActivity) getActivity();
        Log.d(TAG, Integer.toString(mainAct.PlayerChoices.size()));
        linearLayout =  (LinearLayout) v.findViewById(R.id.body);
        draw(mainAct.PlayerChoices.get(mainAct.PlayerChoices.size() - 1));
        return v;
    }

    private void draw(final Choice choice){

        Log.d(TAG,choice.getID());
        Log.d(TAG,choice.getName());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        if(!choice.getTitle().equals("")){
            TextView title = new TextView(getActivity());
            title.setText(choice.getTitle());
            title.setLayoutParams(params);
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            title.setTypeface(Typeface.DEFAULT_BOLD);
            title.setPadding(0, padding(20), 0, padding(20));
            title.setGravity(Gravity.CENTER_HORIZONTAL);
            (linearLayout).addView(title);
        }

        TextView body = new TextView(getActivity());
        body.setText(choice.getContent());
        body.setVerticalScrollBarEnabled(false);
        params.setMargins(0, 0, 0, padding(50));
        body.setLayoutParams(params);
        body.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        body.setGravity(Gravity.START);
        (linearLayout).addView(body);

        if (choice.getNumberOfChildren() != 0){
            for (int i = 0; i < choice.getNumberOfChildren(); i++){
                Button options = new Button(getActivity());
                options.setText((choice.getChild(i)).getName());
                params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                if (i == choice.getNumberOfChildren()-1)        params.setMargins(0, 0, 0, padding(200));
                else                                            params.setMargins(0, 0, 0, padding(15));
                options.setLayoutParams(params);
                (linearLayout).addView(options);

                final int iter = i;
                options.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "click");
                        ((MainActivity) getActivity()).PlayerChoices.add(choice.getChild(iter));
                        getFragmentManager().popBackStack();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
                        ft.replace(R.id.fragmentContainer, new StoryFragment(), "StoryFragment");
                        ft.addToBackStack(null).commit();
                    }});
            }
        }

    }

    private int padding(int sizeInDp){
        float scale = getResources().getDisplayMetrics().density;
        return ((int) (sizeInDp*scale + 0.5f));
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop()");
        super.onStop();
    }
}