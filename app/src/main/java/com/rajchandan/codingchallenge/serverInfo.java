package com.rajchandan.codingchallenge;

public class serverInfo
{
    private String firstName;
    private String lastName;
    private String avatar;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public serverInfo()
    {

    }

    public serverInfo(String firstName, String lastName, String avatar)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }
}
