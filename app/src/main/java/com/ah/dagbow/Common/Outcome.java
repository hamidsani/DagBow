package com.ah.dagbow.Common;

import android.util.Log;

/**
 * Created by HaMiD on 9/24/2017.
 */

public class Outcome {

    private Character cHero;
    private Object oStat;
    private Enum.change eChange;
    private Object oValue;

    public Outcome(Character hero, Enum.stats stat, Enum.change changeCase, Object value) {
        this.oStat = stat;
        this.eChange = changeCase;
        this.oValue = value;
        this.cHero = hero;
    }

    public void update() {
        switch (eChange) {
            case ADD:
                break;
            case EQUAL:
                cHero.set(Enum.stats.WOUND, oValue);
                Log.d("stats", "update");
                break;
            case SUB:
                break;
        }
    }
}
