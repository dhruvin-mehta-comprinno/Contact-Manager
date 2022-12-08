package com.comp.crud.controller;

import com.comp.crud.entity.Contact;
import com.comp.crud.entity.TokenSignupReq;
import com.comp.crud.entity.User;
import com.comp.crud.repo.ContactRepo;
import com.comp.crud.repo.UserRepo;
import com.comp.crud.service.DataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/contacts")
public class ContactCont {


    @Autowired
    DataService dataService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ContactRepo contactRepo;
    @PostMapping("/add")
    @Operation(summary = "Add a contact", description = "Adds a Contact as per the userid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added"),
            @ApiResponse(code = 404, message = "Sorry!!")
    })
    public Contact addContact(@RequestBody Contact contacts, Principal principal) {

//        Contact savedContact = dataService.saveContact(contacts);
//        return new ResponseEntity<Contact>(savedContact, HttpStatus.CREATED);

        String username = principal.getName();
        User ud = userRepo.findByUsername(username);
        Contact ct = new Contact(userRepo.findByUid((long) Math.toIntExact(ud.getUid())));
        ct.setName(contacts.getName());
        ct.setContactNumber(contacts.getContactNumber());
        ct.setUid(ud.getUid());
        ct.setLastName(contacts.getLastName());
        ct.setEmail(contacts.getEmail());
        return contactRepo.save(ct);








//        return userRepo.save(ut);

    }

    @GetMapping("/allContacts/{pageNumber}/{pageSize}")
    @Operation(summary = "Get all contacts", description = "Get all contacts with pagination")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Sorry!!")
    })
    public ResponseEntity<List<Contact>> getAllContacts(@PathVariable int pageNumber, @PathVariable int pageSize)
    {

        List<Contact> allContacts = dataService.getContacts(pageNumber, pageSize);

        return new ResponseEntity<List<Contact>>(allContacts, HttpStatus.FOUND);


    }
    @GetMapping("/get")
    @Operation(summary = "Get all user contacts", description = "Get all user contacts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Sorry!!")
    })
    public ResponseEntity<?> getAllUserContacts(Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        List<Contact> contacts = userRepo.findContacts((long) Math.toIntExact(user.getUid()));
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Update contact details", description = "Update a Contact by the userid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Sorry!!")
    })
    public Contact updateBook(@RequestBody Contact contact){

        return dataService.updateContact(contact);

    }
    @DeleteMapping("/delete")
    public void deleteContact(@RequestParam int id){
        Contact contactDelete = dataService.getContact(id);
        dataService.deleteContact(id);
        }

//    @GetMapping("{id}")
//    public Optional<Contact> findContactById(@PathVariable("id") int id){
//        return dataService.findById(id);
//
//    }
    @DeleteMapping("/deleteAll")
    @Operation(summary = "Delete all contacts", description = "Delete all contacts by userid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Sorry!!")
    })
    public void deletedAllBooks() {
        contactRepo.deleteAll();
    }

}
