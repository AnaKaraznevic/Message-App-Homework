package com.example.messageapp.controller.statistics;

import java.time.LocalDateTime;

public record UserMessageStatistics(
        Long userId,
        String userName,
        Long numberOfMessages,
        LocalDateTime firstMessageDate,
        LocalDateTime lastMessageDate,
        Double averageTextLength
) {
}
