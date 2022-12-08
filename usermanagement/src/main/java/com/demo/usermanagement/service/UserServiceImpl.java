package com.demo.usermanagement.service;


import com.demo.usermanagement.entity.CombinedObj;

import com.demo.usermanagement.entity.ContCombined;
import com.demo.usermanagement.entity.Contact;
import com.demo.usermanagement.entity.User;
import com.demo.usermanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userRepo;





    @Autowired
    private RestTemplate restTemplate;
    @Override
    public User createUser(User user) throws Exception {
        User demo = userRepo.findByUsername(user.getUsername());

        if(demo!=null) {

            throw new Exception("User already present");

        }
        return userRepo.save(user);
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        if(userRepo.findByUsername(username)!=null){
            return userRepo.findByUsername(username);

        }
        throw new Exception("User does not exist");
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws Exception {

        if(userRepo.findByUsernameAndPassword(username, password)!=null){
            return userRepo.findByUsernameAndPassword(username, password);
        }
        throw new Exception("Invalid credentials");

    }


    @Override
    public void deleteUser(int uid) {
        if(userRepo.existsById(uid)){
            userRepo.deleteById(uid);
        }




    }

    @Override
    public User updateUserDetails(Principal principal, User user) throws Exception {
        String updateDetails = principal.getName();
        User ud = userRepo.findByUsername(updateDetails);
//        Long updatedDetails = user.getUid();
        User ut =userRepo.findByUid((long) Math.toIntExact(ud.getUid()));
        ut.setName(user.getName());
        ut.setUsername(user.getUsername());
        ut.setPassword(user.getPassword());
        return userRepo.save(ut);
    }


//    @Override
//    public List<Contact> getContactsOfUser(User user) {
//        return userRepo.findByUser(user);
//    }

//    @Override
//    public List<Contact> getContactsOfUsers(User user) {
//        return userRepo.findByUser(user);
//    }

    @Override
    public CombinedObj getUserandContacts(Long uid) {
        CombinedObj co = new CombinedObj();
        User user = userRepo.findByUid((long) Math.toIntExact(uid));
        co.setUser(user);
        java.util.List<Contact> listOfContacts = restTemplate.getForObject("http://CONTACT-SERVICE/contacts/"+user.getUid(),List.class);
        co.setContact(listOfContacts);
        return co;
    }
    @Override
    public ContCombined getUserContacts() {
        ContCombined co = new ContCombined();
        java.util.List<Contact> listOfContacts = restTemplate.getForObject("http://CONTACT-SERVICE/contacts/",List.class);
        co.setContact(listOfContacts);
        return co;

    }

//    @Override
//    public CombinedObj getUserContactDetails(String username) {
//        CombinedObj data = new CombinedObj();
//        User user = userRepo.findByUsername(username);
//
//        Contact contact = restTemplate.getForObject("http://localhost:8082/users/" + user.getId() , Contact.class);
//
//        data.setUser(user);
//        data.setContact(contact);
//        return data;
//    }
//    @Override
//    public Contact saveContact(Contact contact) {
//        return contactRepo.save(contact);
//    }
//    @Override
//    public Optional<Contact> findById(int id) {
//        return contactRepo.findById(id);
//    }


}
