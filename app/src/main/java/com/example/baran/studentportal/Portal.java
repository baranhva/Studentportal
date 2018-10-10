package com.example.baran.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class Portal implements Parcelable {
    private String mPortalURL;
    private String mPortalTitle;

    public Portal(String mPortalURL, String mPortalTitle) {
        this.mPortalURL = mPortalURL;
        this.mPortalTitle = mPortalTitle;
    }

    public String getmPortalURL() {
        return mPortalURL;
    }
    public void setmPortalURL(String mPortalURL) {
        this.mPortalURL = mPortalURL;
    }

    public String getmPortalTitle() {
        return mPortalTitle;
    }

    public void setmPortalTitle(String mPortalTitle) {
        this.mPortalTitle = mPortalTitle;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPortalURL);
        dest.writeString(this.mPortalTitle);
    }

    protected Portal(Parcel in) {
        this.mPortalURL = in.readString();
        this.mPortalTitle = in.readString();
    }

    public static final Creator<Portal> CREATOR = new Creator<Portal>() {
        @Override
        public Portal createFromParcel(Parcel source) {
            return new Portal(source);
        }

        @Override
        public Portal[] newArray(int size) {
            return new Portal[size];
        }
    };
}