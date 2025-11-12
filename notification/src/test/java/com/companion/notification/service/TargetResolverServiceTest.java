package com.companion.notification.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.companion.notification.dto.out.MessageTarget;

@ExtendWith(MockitoExtension.class)
class TargetResolverServiceTest {
    

    @Test
    void resolveTargets_withUserType_returnsIds() {
        Collection<String> users = List.of("1");

        Collection<String> resolvedTargets = new TargetResolverService().resolveTargets(
            new MessageTarget(users, "user")
        );

        assertEquals(users, resolvedTargets);
    }

    @Test
    void resolveTargets_withNullTarget_throwsIllegalArgumentException() {
        TargetResolverService service = new TargetResolverService();
        assertThrows(IllegalArgumentException.class, () -> service.resolveTargets(null));
    }

}
