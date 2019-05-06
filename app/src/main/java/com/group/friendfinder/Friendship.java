package com.group.friendfinder;

public class Friendship {

    private String friendshipid;
    private String startingDate;
    private String endingDate;
    private Profile ffriendid;
    private Profile fstudentid;

    public Friendship() {
    }

    public Friendship(String friendshipid, String startingDate, String endingDate, Profile ffriendid, Profile fstudentid) {
        this.friendshipid = friendshipid;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.ffriendid = ffriendid;
        this.fstudentid = fstudentid;
    }

    public String getFriendshipid() {
        return friendshipid;
    }

    public void setFriendshipid(String friendshipid) {
        this.friendshipid = friendshipid;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public Profile getFfriendid() {
        return ffriendid;
    }

    public void setFfriendid(Profile ffriendid) {
        this.ffriendid = ffriendid;
    }

    public Profile getFstudentid() {
        return fstudentid;
    }

    public void setFstudentid(Profile fstudentid) {
        this.fstudentid = fstudentid;
    }
}
