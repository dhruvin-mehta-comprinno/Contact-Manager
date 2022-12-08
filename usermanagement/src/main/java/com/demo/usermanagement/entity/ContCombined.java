package com.demo.usermanagement.entity;

import java.util.List;

public class ContCombined {
    private List<Contact> contact;

    @Override
    public String toString() {
        return "ContCombined{" +
                "contact=" + contact +
                '}';
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

    public ContCombined() {
    }

    public ContCombined(List<Contact> contact) {
        this.contact = contact;
    }
}
