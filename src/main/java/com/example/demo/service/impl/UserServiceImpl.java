package com.example.demo.service.impl;

import com.example.demo.model.Authority;
import com.example.demo.model.User;
import com.example.demo.model.enums.Status;
import com.example.demo.model.exceptions.DuplicateDataException;
import com.example.demo.model.exceptions.InternalErrorException;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Constants;
import com.example.demo.util.MailSenderClient;
import com.example.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private MailSenderClient mailClient;


    @Override
    public void add(User user) throws DuplicateDataException, InternalErrorException {

        try {
            User duplicate = userRepository.getByEmail(user.getEmail());
            if (duplicate != null) {
                throw new DuplicateDataException("There is user with this email");
            }

            user.setStatus(Status.Unverified);
            user.setCode(Util.generateRandomChars());
            user.setPassword(Util.getEncoded(user.getPassword()));
            Authority authority = authorityRepository.getByName(Constants.ROLE_USER);
            userRepository.save(user);
            user.setAuthorities(Collections.singleton(authority));

            mailClient.send(user.getEmail(), Constants.EMAIL_SUBJECTS_VERIFICATION, Util.getVerificationMessage(user.getName(), user.getCode()));
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }



    }

    @Override
    public void verify(String email, String verificationCode) {

    }

    @Override
    public void sendRecoveringCode(String email) {

    }

    @Override
    public void changePassword(String email, String password, String recoveringCode) {

    }

    @Override
    public void resendVerificationCode(String email) {

    }

    @Override
    public User getByEmail(String email) {
        return null;
    }
}
