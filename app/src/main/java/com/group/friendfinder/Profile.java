package com.group.friendfinder;

public class Profile {

    private Integer studentid;
    private String smonashEmail;
    private String spassword;
    private String firstname;
    private String surname;
    private String dateOfBirth;
    private Character gender;
    private String nationality;
    private String nativeLanguage;
    private String address;
    private String suburb;
    private String course;
    private Character studyMode;
    private String job;
    private String favorSport;
    private String favorMovie;
    private String favorUnit;
    private String subscribeData;
    private String subscribeTime;

    public Profile() {
    }

    public Profile(Integer studentid, String smonashEmail, String spassword, String firstname, String surname, String dateOfBirth, Character gender, String nationality, String nativeLanguage, String address, String suburb, String course, Character studyMode, String job, String favorSport, String favorMovie, String favorUnit, String subscribeData, String subscribeTime) {
        this.studentid = studentid;
        this.smonashEmail = smonashEmail;
        this.spassword = spassword;
        this.firstname = firstname;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.nativeLanguage = nativeLanguage;
        this.address = address;
        this.suburb = suburb;
        this.course = course;
        this.studyMode = studyMode;
        this.job = job;
        this.favorSport = favorSport;
        this.favorMovie = favorMovie;
        this.favorUnit = favorUnit;
        this.subscribeData = subscribeData;
        this.subscribeTime = subscribeTime;
    }

    /*
    *** getter
    */
    public Integer getStudentid() {
        return studentid;
    }

    public String getSmonashEmail() {
        return smonashEmail;
    }

    public String getSpassword() {
        return spassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Character getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public String getAddress() {
        return address;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCourse() {
        return course;
    }

    public Character getStudyMode() {
        return studyMode;
    }

    public String getJob() {
        return job;
    }

    public String getFavorSport() {
        return favorSport;
    }

    public String getFavorMovie() {
        return favorMovie;
    }

    public String getFavorUnit() {
        return favorUnit;
    }

    public String getSubscribeData() {
        return subscribeData;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    /*
     *** setter
     */
    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public void setSmonashEmail(String smonashEmail) {
        this.smonashEmail = smonashEmail;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setStudyMode(Character studyMode) {
        this.studyMode = studyMode;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setFavorSport(String favorSport) {
        this.favorSport = favorSport;
    }

    public void setFavorMovie(String favorMovie) {
        this.favorMovie = favorMovie;
    }

    public void setFavorUnit(String favorUnit) {
        this.favorUnit = favorUnit;
    }

    public void setSubscribeData(String subscribeData) {
        this.subscribeData = subscribeData;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
}
