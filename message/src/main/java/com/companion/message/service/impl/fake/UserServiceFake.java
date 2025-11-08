package com.companion.message.service.impl.fake;

import org.springframework.stereotype.Service;

import com.companion.message.service.contract.UserService;

@Service
public class UserServiceFake implements UserService{

    @Override
    public String getConnectedUserId() {
        return "1";
    }
    
}
