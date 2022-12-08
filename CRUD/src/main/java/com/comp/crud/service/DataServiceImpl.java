package com.comp.crud.service;

import com.comp.crud.entity.Contact;
import com.comp.crud.repo.ContactRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.PageAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    ContactRepo contactRepo;

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepo.save(contact);
    }
    @Override
    public List<Contact> getContacts(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Contact> ct = contactRepo.findAll(page);
        return ct.toList();


    }



    @Override
    public Contact updateContact(Contact contact) {
        Integer updatedData = contact.getId();
        Contact ct = contactRepo.findById(updatedData).get();
        ct.setContactNumber(contact.getContactNumber());
        ct.setEmail(contact.getEmail());
        ct.setLastName(contact.getLastName());
        ct.setName(contact.getName());
        return contactRepo.save(ct);
    }

    @Override
    public void deleteContact(int id) {
        if(contactRepo.existsById(id)){
            contactRepo.deleteById(id);
        }

    }

    @Override
    public Contact getContact(int id) {
        Optional<Contact> contactSearch = contactRepo.findById(id);
        if(contactSearch.isPresent()) {
            return contactSearch.get();
        }
        else {
            return null;
        }
    }

    @Override
    public List<Contact> findContactByUid(int uid) {
        return contactRepo.findByUid((long) uid);
    }

    @Override
    public Optional<Contact> findById(int id) {
        return contactRepo.findById(id);
    }

    @Override
    public void deleteAllContacts(Contact contact) {
        contactRepo.deleteAll();
    }



}
