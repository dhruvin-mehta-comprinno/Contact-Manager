package com.comp.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="CONTACTS_DETAILS")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String lastName;
    private Long contactNumber;
    private String email;
    private Long uid;

    public Contact(User byUid) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Contact() {
    }

    public Contact(int id, String name, String lastName, Long contactNumber, String email, Long uid) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.uid = uid;
    }
    public Contact(String name, String lastName, String email) {

        this.name = name;
        this.lastName = lastName;

        this.email = email;

    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNumber=" + contactNumber +
                ", email='" + email + '\'' +
                ", uid=" + uid +
                '}';
    }
}
