package com.education.capstone_stage2.model;

/**
 * Created by Sara on 9/15/2017.
 */

public class News {

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
    private NewsType type;

    public NewsType getType() {
        return type;
    }

    public void setType(NewsType type) {
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
}
