package com.example.medicalapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private String id, name, town, date, email, phone, snils, passport, password;

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            String id = source.readString();
            String name = source.readString();
            String town = source.readString();
            String date = source.readString();
            String email = source.readString();
            String phone = source.readString();
            String snils = source.readString();
            String passport = source.readString();
            String password = source.readString();
            return new Person(id, name, town, date, email, phone, snils, passport, password);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public Person(String id, String name, String town, String date, String email, String phone, String snils, String passport, String password) {
        this.id = id;
        this.name = name;
        this.town = town;
        this.date = date;
        this.email = email;
        this.phone = phone;
        this.snils = snils;
        this.passport = passport;
        this.password = password;
    }

    public Person() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(town);
        parcel.writeString(date);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(snils);
        parcel.writeString(passport);
        parcel.writeString(password);

    }
}
