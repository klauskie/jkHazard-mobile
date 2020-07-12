package com.klaus.jkhazard.model;

public class Card {

    private int mId;
    private int mImageResourceID;
    private boolean isNormal;
    private boolean isScrapped;

    public Card(){}

    public Card(int mId, int mImageRes, boolean isNormal, boolean isScrapped) {
        this.mId = mId;
        this.mImageResourceID = mImageRes;
        this.isNormal = isNormal;
        this.isScrapped = isScrapped;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public void setImageResourceID(int mImageFileName) {
        this.mImageResourceID = mImageFileName;
    }

    public boolean isNormal() {
        return isNormal;
    }

    public void setNormalType(boolean normal) {
        isNormal = normal;
    }

    public boolean isScrapped() {
        return isScrapped;
    }

    public void setScrapped(boolean scrapped) {
        isScrapped = scrapped;
    }
}
