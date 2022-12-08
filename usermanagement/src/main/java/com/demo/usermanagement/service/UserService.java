package com.demo.usermanagement.service;


import com.demo.usermanagement.entity.CombinedObj;

import com.demo.usermanagement.entity.ContCombined;
import com.demo.usermanagement.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService{

    public User createUser(User user) throws Exception;

    public User getUserByUsername(String username) throws Exception;
    public User getUserByUsernameAndPassword(String username, String password) throws Exception;
    public void deleteUser(int uid);
    public User updateUserDetails(Principal principal,User user) throws Exception;
//    public List<Contact> getContactsOfUser(User user);
//    public List<Contact> getContactsOfUser()

   public CombinedObj getUserandContacts(Long uid);

   public ContCombined getUserContacts();
//    public CombinedObj getUserContactDetails(String username);
//    public Contact saveContact(Contact contact);
//    public Optional<Contact> findById(int id);







}
