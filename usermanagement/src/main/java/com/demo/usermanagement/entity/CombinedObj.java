package com.demo.usermanagement.entity;


import com.demo.usermanagement.entity.User;
import jdk.jfr.DataAmount;

import java.util.List;



public class CombinedObj {
    @Override
    public String toString() {
        return "CombinedObj{" +
                "user=" + user +
                ", contact=" + contact +
                '}';
    }

    public CombinedObj(User user, List<Contact> contact) {
        this.user = user;
        this.contact = contact;
    }

    public CombinedObj() {
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

    private List<Contact> contact;


}
