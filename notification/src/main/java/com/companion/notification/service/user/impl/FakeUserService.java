package com.companion.notification.service.user.impl;

import org.springframework.stereotype.Service;

import com.companion.notification.service.user.UserService;

@Service
public class FakeUserService implements UserService{
    
    @Override
    public String getUserEmailById(String userId) {
        return "fakeuser@example.com";
    }
}
