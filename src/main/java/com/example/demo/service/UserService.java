package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.exceptions.DuplicateDataException;
import com.example.demo.model.exceptions.InternalErrorException;

public interface UserService {


    void add(User user) throws DuplicateDataException, InternalErrorException;

    void verify(String email,String verificationCode) ;

    void sendRecoveringCode(String email) ;

    void changePassword(String email,String password,String recoveringCode);

    void resendVerificationCode(String email) ;

    User getByEmail(String email) ;
}
