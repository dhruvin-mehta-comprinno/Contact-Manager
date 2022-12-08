package com.demo.usermanagement.controller;

import com.demo.usermanagement.config.MySecurityUtils;
//import com.demo.usermanagement.entity.CombinedObj;

import com.demo.usermanagement.entity.CombinedObj;

import com.demo.usermanagement.entity.ContCombined;
import com.demo.usermanagement.entity.Contact;
import com.demo.usermanagement.entity.User;
import com.demo.usermanagement.repo.UserRepo;
import com.demo.usermanagement.service.UserDetailService;
import com.demo.usermanagement.service.UserService;
import com.demo.usermanagement.tokens.TokenReq;
import com.demo.usermanagement.tokens.TokenRes;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RestTemplate restTemplates;



//    @PostMapping("/register")
//    public User createUser(@RequestBody User user) throws Exception {
//        String data = user.getUsername();
//        if(data!=null){
//            User registeredUser = userService.getUserByUserName(data);
//            if(registeredUser!=null){
//                throw new Exception("Sorry!! User already present");
//            }
//        }
//        return userService.saveUser(user);
//    }

//    @PostMapping("/")
//    public User registerUser(@RequestBody User user) throws Exception {
//
//        return userService.createUser(user);
//    }
//
//    @PostMapping("/login")
//    public User loginUser(@RequestBody User user) throws Exception{
//
//
//        return userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
//
//    }
    @Autowired
    UserRepo userRepo;


//    @GetMapping("/{username}")
//    public User getUser(@PathVariable("username") String username) throws Exception {
//
//        return userService.getUserByUsername(username);
//    }
//    @GetMapping("/index")
//    public String newIndex(){
//        return "hello world";
//    }

//    @DeleteMapping("delete/{uid}")
//    public void deleteUser(@PathVariable("uid") int uid) {
//        userService.deleteUser(uid);
//
//    }
//to return user details and contact details
//    @GetMapping("/contact/{username}")
//    public CombinedObj getUserandContacts(@PathVariable("username") String username){
//        return userService.getUserandContacts(username);
//
//    }
//    @GetMapping("/contact/{uid}")
//    public ResponseEntity<CombinedObj> getAllUserContacts(@PathVariable("uid") Long uid){
//        CombinedObj co = userService.getUserandContacts(uid);
//        return new ResponseEntity<CombinedObj>(co,HttpStatus.OK);
//
////        CombinedObj co = new CombinedObj();
////        User user = userRepo.findByUid(uid);
////        co.setUser(user);
////
////        java.util.List<Contact> listOfContacts = restTemplates.getForObject("http://CONTACT-SERVICE/contacts/"+uid,List.class);
////        co.setContact(listOfContacts);
////        return new ResponseEntity<CombinedObj>(co,HttpStatus.FOUND);
//
//
//    }

//    @GetMapping("/")
//    @Operation(summary = "Get all user contacts", description = "Get all user contacts")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully retrieved"),
//            @ApiResponse(code = 404, message = "Sorry!!")
//    })
//    public ResponseEntity<?> getAllUserContacts(Principal principal) {
//        String username = principal.getName();
//        User user = userRepo.findByUsername(username);
//        List<Contact> contacts = userRepo.findContacts((long) Math.toIntExact(user.getUid()));
//        return new ResponseEntity<>(contacts, HttpStatus.OK);
//
//
//    }

//        ContCombined co = userService.getUserContacts();
//        return new ResponseEntity<ContCombined>(co, HttpStatus.OK);

//    @PostMapping("/contact/{username}/add")
//    public ResponseEntity<Contact> addContact(@RequestBody Contact contact, @PathVariable("username") String username){
//        Contact savedContact = userService.saveContact(contact);
//        return new ResponseEntity<Contact>(savedContact,HttpStatus.CREATED);
//    }
//    @GetMapping("/get/{id}")
//    public Optional<Contact> findContactById(@PathVariable("id") int id){
//        return userService.findById(id);
//    }
//    @GetMapping("/contact/added/{username}")
//    public CombinedObj getUserContactDetails(@PathVariable("username") String username){
//        return userService.getUserContactDetails(username);
//
//    }

    @PutMapping("/update")
    @Operation(summary = "Update user details", description = "Update user details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Sorry!!")
    })
    public User updateUser(@RequestBody User user, Principal principal) throws Exception {
        String updateDetails = principal.getName();
        User ud = userRepo.findByUsername(updateDetails);
        User ut =userRepo.findByUid((long) Math.toIntExact(ud.getUid()));
        ut.setName(user.getName());
        ut.setUsername(user.getUsername());
        ut.setPassword(user.getPassword());
        return userRepo.save(ut);
//        return userService.updateUserDetails(user);

    }
//@PutMapping("/update")
//public User getUpdatedDetails(Principal principals, User users){
//    String updatedDetails = principals.getName();
//    User user = userRepo.findByUsername(updatedDetails);
////    User ut =userRepo.findByUid((long) Math.toIntExact(user.getUid()));
//    user.setName(users.getName());
//    user.setUsername(users.getUsername());
//    user.setPassword(users.getPassword());
//
//    return userRepo.save(user);
//
//}





//        User update = userService.updateUserDetails(user);
//        return new ResponseEntity<User>(update, HttpStatus.CREATED);

    }

//    @GetMapping("/{username}")
//    public User getUser(@PathVariable("username") String username) throws Exception {
//        return userService.getUserByUsername(username);
//    }
//    @GetMapping("/current-user")
//    public User getCurrentUser(Principal principal) {
//        return ((User)this.userDetailService.loadUserByUsername(principal.getName()));
//    }


//    @GetMapping("/userName/{uid}")
//    public ResponseEntity<?> getContactsOfUser(@PathVariable("uid") int uid) {
//        User user = new User();
//        user.setUid(uid);
//        List<Contact> ct = userService.getContactsOfUser(user);
//        return ResponseEntity.ok(ct);
//
//        List<Contact> ct = user.getContacts();
//        List list = new ArrayList(ct);









