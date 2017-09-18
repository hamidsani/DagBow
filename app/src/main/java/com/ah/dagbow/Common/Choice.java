package com.ah.dagbow.Common;

import com.ah.dagbow.Common.Character;

//import com.scalified.tree.TreeNode;
//import com.scalified.tree.multinode.ArrayMultiTreeNode;
/**
 * Created by HaMiD on 7/23/2017.
 */

public class Choice {

    private String sID = "";
    private String sName = "";
    private String sContent = "";
    private String sTitle = "";
    private int iChildrenNum = 0;
    private int iParentsNum = 0;
    private Choice[] children = new Choice[5];
    private Choice[] parents = new Choice[5];

    public Choice(String ID, String name, String content, String title) {
        this.sID = ID;
        this.sName = name;
        this.sContent = content;
        this.sTitle = title;
    }

    public Choice(String ID, String name, String content) {
        this.sID = ID;
        this.sName = name;
        this.sContent = content;
    }

    public String getID(){ return this.sID;}
    public String getContent(){ return this.sContent;}
    public String getName(){ return this.sName;}
    public String getTitle(){ return this.sTitle;}

    public void addChild(Choice child){
        children[this.iChildrenNum++] = child;
        child.addParent(this);
    }

    public void addParent(Choice parent){
        parents[this.iParentsNum++] = parent;
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
}
