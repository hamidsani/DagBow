package com.ah.dagbow.Common;

import android.content.Context;
import android.content.res.Resources;

import com.ah.dagbow.MainActivity;
import com.ah.dagbow.R;

/**
 * Created by HaMiD on 9/17/2017.
 * this class contains the attributes of the character throughout the game
 */

public class Character extends MainActivity {
    private String name;
    private Enum.wound wound;
    private int chapter;

    public Character() {
        wound = Enum.wound.OPEN;
    }

    public void set(Enum.stats inputEnum, Object value) {
        switch (inputEnum) {
            case NAME:
                name = (String) value;
            case WOUND:
                wound = (Enum.wound) value;
                break;
            case CHAPTER:
                chapter = (int) value;
                break;
        }
    }

    public Object get(Enum.stats inputEnum) {
        Object output = new Object();
        switch (inputEnum) {
            case NAME:
                output = name;
                break;
            case WOUND:
                output = wound;
                break;
            case CHAPTER:
                output = chapter;
                break;
        }

        return output;
    }


    public String getFieldName(Enum.stats inputEnum){
        String output = "";
        switch (inputEnum) {
            case NAME:
                output = "Name: ";
                break;
            case WOUND:
                output = "Attributes:    ";
                break;
            case CHAPTER:
                output = "Chapter:";
                break;
        }

        return output;
    }

    public String getField(Enum.stats inputEnum){
        String output = "";
        switch (inputEnum) {
            case NAME:
                output = "Joth";
                break;
            case WOUND:
                if (MainActivity.hero.get(Enum.stats.WOUND) == Enum.wound.OPEN)                 output = "Bleeding";
                else if (MainActivity.hero.get(Enum.stats.WOUND) == Enum.wound.CAUTERIZED)      output = "CAUTERIZED";
                else if (MainActivity.hero.get(Enum.stats.WOUND) == Enum.wound.STITCHED)        output = "Stitched";
                else                                                                            output = "Healthy";
                break;
            case CHAPTER:
                output = "1";
                break;
        }

        return output;
    }

    public int getNumFields(){
        return Enum.stats.values().length;
    }

    public void reset() {
        name = "Joth";
        wound = Enum.wound.OPEN;
        chapter = 1;
    }
}
