package com.companion.notification.dto.out;

import java.util.Collection;

public record MessageTarget(Collection<String> ids, String type) {
}
