package com.companion.notification.converter;

import java.util.Arrays;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.companion.notification.domain.NotificationType;

@Component
public class StringToNotificationType implements Converter<String, NotificationType> {

    @Override
    public NotificationType convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            throw new IllegalArgumentException("Le type ne peut pas être vide.");
        }
        try {
            return NotificationType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                String.format("Le type '%s' n'est pas valide. Valeurs autorisées : %s",
                    source,
                    Arrays.toString(NotificationType.values())
                )
            );
        }
    }
}
