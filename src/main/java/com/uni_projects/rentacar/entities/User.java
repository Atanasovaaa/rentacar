package com.uni_projects.rentacar.entities;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private int age;
    private int hasCrashes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int isHasCrashes() {
        return hasCrashes;
    }

    public void setHasCrashes(int hasCrashes) {
        this.hasCrashes = hasCrashes;
    }
}
