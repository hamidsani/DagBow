package com.ah.dagbow.Common;

import android.util.Log;

/**
 * Created by HaMiD on 7/23/2017.
 */

public class Choice {

    private String sID = "";            // unique identifier
    private String sName = "";          // Name of the choice. this is displayed when option is given to the user
    private String sPreText = "";       // static text before content, only used if there is a dynamic content
    private String sContent = "";       // dynamic content that could contain micro choices
    private String sPostText = "";      // static text after content, only used if there is a dynamic content
    private String sTitle = "";         // if the choice has a title associated with it
    private int iChildrenNum = 0;       // number of children of the choice
    private int iParentsNum = 0;        // number of parents of the choice
    private int iMicroChoiceNum = 0;    // number of microChoices in the text
    private int iOutcomeNum = 0;        // number of outcomes if the choice is selected (normally outcomes apply to the character)
    private Choice[] children = new Choice[5];
    private Choice[] parents = new Choice[5];
    private MicroChoice[] uChoices = new MicroChoice[5];
    private Outcome[] outcomes = new Outcome[5];

    public Choice(String ID, String name, String preText, String content, String postText, String title) {
        this.sID = ID;
        this.sName = name;
        this.sPreText = preText;
        this.sContent = content;
        this.sPostText = postText;
        this.sTitle = title;
    }

    public Choice(String ID, String name, String preText, String content, String postText) {
        this(ID, name, preText, content, postText, "");
    }

    public Choice(String ID, String name, String content, String title) {
        this(ID, name, "", content, "", title);
    }

    public Choice(String ID, String name, String content) {
        this(ID, name, "", content, "", "");
    }


    public String getID(){ return this.sID;}

    public String getContent() {
        update();
        if (iMicroChoiceNum != 0) {
            return this.sPreText + this.sContent + this.sPostText;
        } else {
            return this.sContent;
        }
    }
    public String getName(){ return this.sName;}
    public String getTitle(){ return this.sTitle;}

    public void addChild(Choice child){
        children[this.iChildrenNum++] = child;
        child.addParent(this);
    }

    public void addParent(Choice parent){
        parents[this.iParentsNum++] = parent;
    }

    public void addMicroChoice(MicroChoice uChoice) {
        uChoices[this.iMicroChoiceNum++] = uChoice;
    }

    public void addOutcome(Outcome outcome) {
        outcomes[this.iOutcomeNum++] = outcome;
    }

    public Choice[] getChildren(){
        return this.children;
    }
    public Choice getChild(int index){
        return this.children[index];
    }

    public Choice[] getParents(){
        return this.parents;
    }

    public int getNumberOfChildren(){       return iChildrenNum;    }
    public int getNumberOfParents(){       return iParentsNum;    }

    private void update() {
        Log.d("stats", "Outcome: " + Integer.toString(iOutcomeNum));
        // update all the outcomes resulted from this choice
        for (int i = 0; i < iOutcomeNum; i++) {
            outcomes[i].update();
        }

        Log.d("stats", "microChoice: " + Integer.toString(iMicroChoiceNum));
        // update the context based on the character so far
        if (iMicroChoiceNum != 0) this.sContent = "";
        for (int i = 0; i < iMicroChoiceNum; i++) {
            this.sContent += uChoices[i].getOutput();
        }
    }
}
