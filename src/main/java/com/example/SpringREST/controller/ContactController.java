package com.example.SpringREST.controller;

import com.example.SpringREST.entity.User;
import com.example.SpringREST.model.request.CreateContactRequest;
import com.example.SpringREST.model.response.ContactResponse;
import com.example.SpringREST.model.response.WebResponse;
import com.example.SpringREST.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(
            path = "/api/contacts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> create(User user, @RequestBody CreateContactRequest request){
        ContactResponse contactResponse = contactService.create(user, request);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @GetMapping(
            path = "/api/contacts/{idContact}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> getContact(User user, @PathVariable String idContact){
        ContactResponse contactResponse = contactService.get(user, idContact);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @PatchMapping(
            path = "/api/contacts/{idContact}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> updateContact(User user, @PathVariable String idContact, @RequestBody CreateContactRequest request){
        ContactResponse contactResponse = contactService.update(user, request, idContact);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @DeleteMapping(
            path = "/api/contacts/{idContact}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> deleteContact(User user, @PathVariable String idContact){
        contactService.deleteContact(user, idContact);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
