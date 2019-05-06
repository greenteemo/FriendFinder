package com.group.friendfinder;

public class Location {
    private Integer locationid;
    private Profile studentid;
    private String locationName;
    private Double latitude;
    private Double longitude;
    private String updateDate;
    private String updateTime;

    public Location() {
    }

    public Location(Integer locationid, Profile studentid, String locationName, Double latitude, Double longitude, String updateDate, String updateTime) {
        this.locationid = locationid;
        this.studentid = studentid;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;

        this.updateDate = updateDate;
        this.updateTime = updateTime;
    }

    public Profile getStudentid() {
        return studentid;
    }

    public void setStudentid(Profile studentid) {
        this.studentid = studentid;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
