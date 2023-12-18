package com.example.groupproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;


@Entity(tableName = "UserProfile")
public class Profile {
    @PrimaryKey(autoGenerate = true)
    private int id; // This is your index column
    @ColumnInfo(name = "UserId")
    @NotNull
    private String userId;
    @ColumnInfo(name = "PhoneNumber")
    private String phoneNumber;
    @ColumnInfo(name = "FirstName")
    private String firstName;
    @ColumnInfo(name = "LastName")
    private String lastName;
    @ColumnInfo(name = "City")
    private String city;
    @ColumnInfo(name = "District")
    private String district;
    @ColumnInfo(name = "Address")
    private String address;
    @ColumnInfo(name = "Age")
    private int age;
    @ColumnInfo(name = "Password")
    private String password;
    @ColumnInfo(name = "Specialty")
    private String specialty;

    @Ignore
    private static int patient, doctor, nurse; // keeping count of the number of doctor in the hospital
    // Empty constructor for Room
    public Profile() {
    }

    public Profile(String role, String phoneNumber, String firstName, String lastName, String city, String district, String address, int age, String password){
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.district = district;
        this.address = address;
        this.age = age;
        this.password = password;
        this.userId = role.substring(0, 1) + String.valueOf(GetInformation.getIndex() + 1);
        this.specialty = null;
    }

    public Profile(String phoneNumber, String firstName, String lastName, String city, String district, String address, int age, String password, String userId){
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.district = district;
        this.address = address;
        this.age = age;
        this.password = password;
        this.userId = userId;
        this.specialty = null;
    }

    public Profile(String phoneNumber, String firstName, String lastName, String city, String district, String address, String specialty, String password, String userid, int age){
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.district = district;
        this.address = address;
        this.age = age;
        this.password = password;
        this.specialty = specialty;
        this.userId = userid;
    }

    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
