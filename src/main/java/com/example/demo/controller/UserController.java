package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.model.exceptions.DuplicateDataException;
import com.example.demo.model.exceptions.InternalErrorException;
import com.example.demo.service.UserService;
import com.example.demo.util.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping(UrlConstants.USERS_CONTROLLER_URL)
public class UserController {


    @Autowired
    private UserService userService;


    @PutMapping
    public ResponseEntity add(@Valid @RequestBody User user) {

        try {

            userService.add(user);

        } catch (DuplicateDataException e) {
            return ResponseEntity.status(409).body(Collections.singletonMap("message",e.getMessage()));

        } catch (InternalErrorException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

        return ResponseEntity.ok().body(user);

    }


}
