package com.comp.crud.service;

import com.comp.crud.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DataService {
    public List<Contact> getContacts(int pageNumber, int pageSize);
    public Contact saveContact(Contact contact);
    public Contact updateContact(Contact contact);
    public void deleteContact(int id);
    public Contact getContact(int id);

    public List<Contact> findContactByUid(int uid);
    public Optional<Contact> findById(int id);
    public void deleteAllContacts(Contact contact);





}
