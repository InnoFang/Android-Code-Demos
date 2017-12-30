package com.example.innf.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * @author Inno Fang
 * @time 2016/7/2 16:19
 * @description CriminalIntent模型层
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    private String mSuspect;

    public Crime() {
        this(UUID.randomUUID());
//        mId = UUID.randomUUID();
//        mDate = new Date();
    }

    public Crime(UUID id){
        mId = id;
        mDate = new Date();
    }

    public Date getDate() {
//        return new SimpleDateFormat("yyyy/MM/dd EE HH:mm:ss ").format(mDate);
//        return (String) new DateFormat().format("EEEE,MMM dd,yyyy",mDate);
        return mDate;
    }
    public boolean isSolved() {
        return mSolved;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {

        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
