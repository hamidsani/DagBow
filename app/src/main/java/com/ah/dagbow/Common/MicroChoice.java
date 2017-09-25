package com.ah.dagbow.Common;

/**
 * Created by HaMiD on 9/24/2017.
 */

public class MicroChoice {

    private String sID = "";
    private String sContent = "";
    private Character hero;
    private String sOutput = "";

    private Enum.stats cStat;
    private Object tConditional;

    public MicroChoice(String ID, String content, Character hero, Enum.stats stat, Object conditional) {
        this.sID = ID;
        this.sContent = content;
        this.hero = hero;
        this.cStat = stat;
        this.tConditional = conditional;
    }

    public String getOutput() {
        switch (cStat) {
            case WOUND:
                if (hero.wound == tConditional) sOutput = sContent;
                break;
            default:
                sOutput = "";
                break;
        }
        return sOutput;
    }
}
