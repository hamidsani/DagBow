package com.ah.dagbow;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ah.dagbow.Common.Choice;
import com.ah.dagbow.Common.Constants;
import com.ah.dagbow.Fragments.MainFragment;
import com.ah.dagbow.Fragments.SplashFragment;
import com.ah.dagbow.Fragments.StoryFragment;
import com.ah.dagbow.Common.Choice;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    public int option = 0;

    private boolean nightMode = true;
    private SharedPreferences sharedPref;

    public List<Choice> PlayerChoices = new ArrayList<>();
    public Choice c_001_1, c_002_1, c_003_1, c_004_1, c_004_2, c_005_1,c_005_2,c_005_3,c_006_1,c_006_2,c_006_3,c_006_4,c_006_5,c_006_6,c_006_7;

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

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
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
        c_001_1 = new Choice("n001_1", res.getString(R.string.chapter_1_1s),res.getString(R.string.chapter_1_1) , res.getString(R.string.chapter_1));

        c_002_1 = new Choice("n002_1", res.getString(R.string.chapter_1_2), res.getString(R.string.chapter_1_2t));

        c_003_1 = new Choice("n003_1", res.getString(R.string.chapter_1_3), res.getString(R.string.chapter_1_3t));

        c_004_1 = new Choice("n004_1", res.getString(R.string.chapter_1_4a), res.getString(R.string.chapter_1_4at));
        c_004_2 = new Choice("n004_2", res.getString(R.string.chapter_1_4b), res.getString(R.string.chapter_1_4bt));

        c_005_1 = new Choice("n005_1", res.getString(R.string.chapter_1_5a), res.getString(R.string.chapter_1_5at));
        c_005_2 = new Choice("n005_2", res.getString(R.string.chapter_1_5b), res.getString(R.string.chapter_1_5bt));
        c_005_3 = new Choice("n005_3", res.getString(R.string.chapter_1_5c), res.getString(R.string.chapter_1_5ct));

        c_006_1 = new Choice("n006_1", res.getString(R.string.chapter_1_6a), res.getString(R.string.chapter_1_6at));
        c_006_2 = new Choice("n006_2", res.getString(R.string.chapter_1_6b), res.getString(R.string.chapter_1_6bt));
        c_006_3 = new Choice("n006_3", res.getString(R.string.chapter_1_6c), res.getString(R.string.chapter_1_6ct));
        c_006_4 = new Choice("n006_4", res.getString(R.string.chapter_1_6d), res.getString(R.string.chapter_1_6dt));
        c_006_5 = new Choice("n006_5", res.getString(R.string.chapter_1_6e), res.getString(R.string.chapter_1_6et));
        c_006_6 = new Choice("n006_6", res.getString(R.string.chapter_1_6f), res.getString(R.string.chapter_1_6ft));
        c_006_7 = new Choice("n006_7", res.getString(R.string.chapter_1_6h), res.getString(R.string.chapter_1_6ht));

        // add the vertices
        c_001_1.addChild(c_002_1);

        c_002_1.addChild(c_003_1);

        c_003_1.addChild(c_004_1);
        c_003_1.addChild(c_004_2);

        c_004_1.addChild(c_005_1);
        c_004_1.addChild(c_005_2);
        c_004_1.addChild(c_005_3);
        c_004_2.addChild(c_005_1);
        c_004_2.addChild(c_005_2);
        c_004_2.addChild(c_005_3);

        c_005_1.addChild(c_006_1);
        c_005_1.addChild(c_006_2);
        c_005_1.addChild(c_006_3);
        c_005_2.addChild(c_006_4);
        c_005_2.addChild(c_006_5);
        c_005_2.addChild(c_006_6);
        c_005_3.addChild(c_006_7);
    }
}
