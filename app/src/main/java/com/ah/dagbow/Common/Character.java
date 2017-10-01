package com.ah.dagbow.Common;

/**
 * Created by HaMiD on 9/17/2017.
 * this class contains the attributes of the character throughout the game
 */

public class Character {
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

    public void reset() {
        name = "Joth";
        wound = Enum.wound.OPEN;
        chapter = 1;
    }
}
