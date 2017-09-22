package com.education.capstone_stage2.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sara on 9/15/2017.
 */

public class News implements Parcelable {

    private String enTitle;
    private String arTitle;
    private String enShortDescription;
    private String arShortDescription;
    private String enLongDescription;
    private String arLongDescription;
    private String enDate;
    private String arDate;
    private double latitude;
    private double longitude;
    private String imgURL;
    private String type;

    public News(){}
    protected News(Parcel in) {
        enTitle = in.readString();
        arTitle = in.readString();
        enShortDescription = in.readString();
        arShortDescription = in.readString();
        enLongDescription = in.readString();
        arLongDescription = in.readString();
        enDate = in.readString();
        arDate = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        imgURL = in.readString();
        type = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getEnTitle() {
        return enTitle;
    }

    public String getArTitle() {
        return arTitle;
    }

    public String getEnShortDescription() {
        return enShortDescription;
    }

    public String getArShortDescription() {
        return arShortDescription;
    }

    public String getEnLongDescription() {
        return enLongDescription;
    }

    public String getArLongDescription() {
        return arLongDescription;
    }

    public String getEnDate() {
        return enDate;
    }

    public String getArDate() {
        return arDate;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public void setArTitle(String arTitle) {
        this.arTitle = arTitle;
    }

    public void setEnShortDescription(String enShortDescription) {
        this.enShortDescription = enShortDescription;
    }

    public void setArShortDescription(String arShortDescription) {
        this.arShortDescription = arShortDescription;
    }

    public void setEnLongDescription(String enLongDescription) {
        this.enLongDescription = enLongDescription;
    }

    public void setArLongDescription(String arLongDescription) {
        this.arLongDescription = arLongDescription;
    }

    public void setEnDate(String enDate) {
        this.enDate = enDate;
    }

    public void setArDate(String arDate) {
        this.arDate = arDate;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(enTitle);
        dest.writeString(arTitle);
        dest.writeString(enShortDescription);
        dest.writeString(arShortDescription);
        dest.writeString(enLongDescription);
        dest.writeString(arLongDescription);
        dest.writeString(enDate);
        dest.writeString(arDate);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(imgURL);
        dest.writeString(type);
    }
}
