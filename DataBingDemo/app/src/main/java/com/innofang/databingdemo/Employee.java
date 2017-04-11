package com.innofang.databingdemo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

/**
 * Author: Inno Fang
 * Time: 2017/2/18 20:47
 * Description:
 */

public class Employee extends BaseObservable {

    private String mFirstName;
    private String mLastName;
    private String mAvatar;

    public ObservableBoolean isFired = new ObservableBoolean();

    public Employee(String firstName, String lastName) {
        mFirstName = firstName;
        mLastName = lastName;
        isFired.set(false);
    }

    public Employee(String firstName, String lastName, boolean fired) {
        mLastName = lastName;
        mFirstName = firstName;
        isFired.set(fired);
    }

    @Bindable
    public String getFirstName() {
        return mFirstName;
    }

    @Bindable
    public String getLastName() {
        return mLastName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    public ObservableBoolean getIsFired() {
        return isFired;
    }

    public void setIsFired(ObservableBoolean isFired) {
        this.isFired = isFired;
        notifyChange();
    }

    @Bindable
    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }
}
