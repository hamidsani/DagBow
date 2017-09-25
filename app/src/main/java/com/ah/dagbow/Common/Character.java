package com.ah.dagbow.Common;

/**
 * Created by HaMiD on 9/17/2017.
 * this class contains the attributes of the character throughout the game
 */

public class Character {
    public Enum.wound wound;

    public Character() {
        wound = Enum.wound.OPEN;
    }

    public void set(Enum.stats inputEnum, Object value) {
        switch (inputEnum) {
            case WOUND:
                wound = (Enum.wound) value;
                break;
        }
    }
}
