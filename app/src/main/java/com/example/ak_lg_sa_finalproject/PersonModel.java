package com.example.ak_lg_sa_finalproject;

/**
 * This class is used as data source for a Person object
 * @author Sara Asefi
 * @version 1.0
 */
public final class PersonModel {
    private int id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email_address;
    private String note;

    // Constructors
    public PersonModel(int id, String f_name, String l_name, String phone_number, String email, String note) {
        this.id = id;
        this.first_name = f_name;
        this.last_name = l_name;
        this.phone_number = phone_number;
        this.email_address = email;
        this.note = note;
    }

    public PersonModel() {
    }

    public PersonModel(String f_name, String l_name, String phone_number, String email, String note) {
        this.first_name = f_name;
        this.last_name = l_name;
        this.phone_number = phone_number;
        this.email_address = email;
        this.note = note;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String name) {
        this.first_name = name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String name) {
        this.last_name = name;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone) {
        this.phone_number = phone;
    }

    public String getEmail() {
        return email_address;
    }

    public void setEmail(String email) {
        this.email_address = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // Prints the Contents of a class
    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", first name='" + first_name + '\'' +
                ", last name='" + last_name + '\'' +
                ", phone number=" + phone_number +
                ", email address='" + email_address + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
