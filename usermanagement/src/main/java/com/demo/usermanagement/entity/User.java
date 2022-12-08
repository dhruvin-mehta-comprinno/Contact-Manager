package com.demo.usermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tokenusers")
public class User {

    public User() {
    }

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
//                "contacts=" + contacts +
                "id=" + id +
                '}';
    }

    public User(Long uid, String name, String username, String password) {
        this.uid = uid;
        this.name = name;
        this.username = username;
        this.password = password;
//        this.contacts = contacts;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }


//    public List<Contact> getContacts() {
//        return contacts;
//    }
//
//    public void setContacts(List<Contact> contacts) {
//        this.contacts = contacts;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public Long uid;


    private String name; //name


    private String username;


    private String password;

    @JsonIgnore
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//user== category
    //contact=quiz


    public User(Long uid, String name, String username, String password,String id) {
        this.uid = uid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.id = id;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Contact> contacts = new ArrayList<>();

}