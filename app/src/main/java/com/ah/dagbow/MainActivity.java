package com.ah.dagbow;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ah.dagbow.Common.Character;
import com.ah.dagbow.Common.Choice;
import com.ah.dagbow.Common.Enum;
import com.ah.dagbow.Common.MicroChoice;
import com.ah.dagbow.Common.Outcome;
import com.ah.dagbow.Fragments.MainFragment;
import com.ah.dagbow.Fragments.StoryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    public List<Choice> PlayerChoices = new ArrayList<>();
    public Choice c_001_1, c_002_1, c_003_1, c_004_1, c_004_2, c_005_1,c_005_2,c_006_1,c_006_2,c_007_1,c_007_2;
    public MicroChoice mc_006_1_a, mc_006_1_b, mc_007_1_a, mc_007_1_b;
    public Outcome o_004_1, o_004_2;
    public Character hero = new Character();
    private boolean nightMode = true;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        nightMode = sharedPref.getBoolean("nightMode", true);

        if (!nightMode) {
            Log.d(TAG, "nightMode");
            setTheme(R.style.AppTheme_Dark);
        } else {
            Log.d(TAG, "dayMode");
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.backbone);

        initializeStory();

        // TODO: Add Google sign in to save data

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out,android.R.animator.fade_in, android.R.animator.fade_out);
        ft.add(R.id.fragmentContainer, new MainFragment(), "MainFragment");
        ft.commit();

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void newStoryFunc() {
        Log.d(TAG, "newStoryFunc()");

        PlayerChoices.clear();
        PlayerChoices.add(c_001_1);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
        ft.replace(R.id.fragmentContainer, new StoryFragment(), "StoryFragment");
        ft.addToBackStack(null).commit();
    }

    public void continueFunc() {
        Log.d(TAG, "continueFunc()");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
        ft.replace(R.id.fragmentContainer, new StoryFragment(), "StoryFragment");
        ft.addToBackStack(null).commit();
    }

    public void overviewFunc(){
        Log.d(TAG, "overviewFunc()");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
        ft.replace(R.id.fragmentContainer, new StoryFragment(), "StoryFragment");
        ft.addToBackStack(null).commit();
    }

    public void changeTheme() {
        Log.d(TAG, "changeTheme()");
        if (!nightMode) {
            Log.d(TAG, "nightMode");
            nightMode = true;
            sharedPref.edit().putBoolean("nightMode", true).apply();
        } else {
            Log.d(TAG, "dayMode");
            nightMode = false;
            sharedPref.edit().putBoolean("nightMode", false).apply();
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //Here is the code for turning this activity into immersion mode
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LOW_PROFILE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private void initializeStory(){
        Resources res = getResources();
        c_001_1 = new Choice("n001_1", res.getString(R.string.chapter_1_1),res.getString(R.string.chapter_1_1t) , res.getString(R.string.chapter_1));

        c_002_1 = new Choice("n002_1", res.getString(R.string.chapter_1_2), res.getString(R.string.chapter_1_2t));

        c_003_1 = new Choice("n003_1", res.getString(R.string.chapter_1_3), res.getString(R.string.chapter_1_3t));

        c_004_1 = new Choice("n004_1", res.getString(R.string.chapter_1_4a), res.getString(R.string.chapter_1_4at) + res.getString(R.string.chapter_1_4st));
        o_004_1 = new Outcome(hero, Enum.stats.WOUND, Enum.change.EQUAL, Enum.wound.CAUTERIZED);
        c_004_2 = new Choice("n004_2", res.getString(R.string.chapter_1_4b), res.getString(R.string.chapter_1_4bt) + res.getString(R.string.chapter_1_4st));
        o_004_2 = new Outcome(hero, Enum.stats.WOUND, Enum.change.EQUAL, Enum.wound.STITCHED);

        c_005_1 = new Choice("n005_1", res.getString(R.string.chapter_1_5a), res.getString(R.string.chapter_1_5at));
        c_005_2 = new Choice("n005_2", res.getString(R.string.chapter_1_5b), res.getString(R.string.chapter_1_5bt));

        c_006_1 = new Choice("n006_1", res.getString(R.string.chapter_1_6a), res.getString(R.string.chapter_1_6a_preT), "", res.getString(R.string.chapter_1_6a_postT));
        mc_006_1_a = new MicroChoice("mc_006_1_a", res.getString(R.string.chapter_1_6a_uCa), hero, Enum.stats.WOUND, Enum.wound.CAUTERIZED);
        mc_006_1_b = new MicroChoice("mc_006_1_b", res.getString(R.string.chapter_1_6a_uCb), hero, Enum.stats.WOUND, Enum.wound.STITCHED);
        c_006_2 = new Choice("n006_2", res.getString(R.string.chapter_1_6b), res.getString(R.string.chapter_1_6bt));

        c_007_1 = new Choice("n007_1", res.getString(R.string.chapter_1_7a), res.getString(R.string.chapter_1_7a_preT), "", res.getString(R.string.chapter_1_7a_postT));
        mc_007_1_a = new MicroChoice("mc_007_1_a", res.getString(R.string.chapter_1_7a_uCa), hero, Enum.stats.WOUND, Enum.wound.CAUTERIZED);
        mc_007_1_b = new MicroChoice("mc_007_1_b", res.getString(R.string.chapter_1_7a_uCb), hero, Enum.stats.WOUND, Enum.wound.STITCHED);
        c_007_2 = new Choice("n007_2", res.getString(R.string.chapter_1_7b), res.getString(R.string.chapter_1_7bt));

        // add the vertices
        c_001_1.addChild(c_002_1);

        c_002_1.addChild(c_003_1);

        c_003_1.addChild(c_004_1);
        c_003_1.addChild(c_004_2);

        c_004_1.addOutcome(o_004_1);
        c_004_1.addChild(c_005_1);
        c_004_1.addChild(c_005_2);
        c_004_2.addOutcome(o_004_2);
        c_004_2.addChild(c_005_1);
        c_004_2.addChild(c_005_2);

        c_005_1.addChild(c_006_1);
        c_005_1.addChild(c_006_2);

        c_006_1.addChild(c_007_1);
        c_006_1.addMicroChoice(mc_006_1_a);
        c_006_1.addMicroChoice(mc_006_1_b);
        c_006_1.addChild(c_007_2);

        c_007_1.addMicroChoice(mc_007_1_a);
        c_007_1.addMicroChoice(mc_007_1_b);
    }
}
