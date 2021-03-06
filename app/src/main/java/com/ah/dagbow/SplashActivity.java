package com.ah.dagbow;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ah.dagbow.Common.Constants;
import com.ah.dagbow.Fragments.SplashFragment;

/**
 * This activity solely starts the first splash screen of the app
 */
public class SplashActivity extends AppCompatActivity {
    private final static String TAG = SplashActivity.class.getSimpleName();
    //Handler that sends a warning when 15 seconds is left to canceling - based off of detecting no interaction
    Handler splashHandler = new Handler();
    Runnable splashRunnable = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "runnable");
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backbone);

        SplashFragment mSplashFragment = new SplashFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.add(R.id.fragmentContainer, mSplashFragment, "SplashFragment");
        ft.addToBackStack(null).commit();

        splashHandler.postDelayed(splashRunnable, Constants.SPLASH_TIMEOUT);

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

}
