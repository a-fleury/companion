package com.companion.notification.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.companion.notification.dto.out.MessageTarget;

@Service
public class TargetResolverService {

    public Collection<String> resolveTargets(MessageTarget target) throws IllegalArgumentException, UnsupportedOperationException {
        if (target == null) {
            throw new IllegalArgumentException("MessageTarget cannot be null");
        }
        if (target.type().equals("user")) {
            return target.ids();
        }
        throw new UnsupportedOperationException("Unsupported target type: " + target.type());
    }
}
