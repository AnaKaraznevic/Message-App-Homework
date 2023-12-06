package com.example.messageapp.controller.statistics;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StatisticsMapper {

    public static List<UserMessageStatistics> mapToUserMessageStatistics(List<Object[]> rawStatistics) {
        return rawStatistics.stream().map(row -> {
            var userId = (Long) row[0];
            var userName = (String) row[1];
            var numberOfMessages = (Long) row[2];
            var firstMessageDate = Optional.ofNullable((Timestamp) row[3])
                    .map(Timestamp::toLocalDateTime)
                    .orElse(null);
            var lastMessageDate = Optional.ofNullable((Timestamp) row[4])
                    .map(Timestamp::toLocalDateTime)
                    .orElse(null);
            var averageTextLength = Optional.ofNullable((BigDecimal) row[5])
                    .map(BigDecimal::doubleValue)
                    .orElse(null);
            return new UserMessageStatistics(
                    userId,
                    userName,
                    numberOfMessages,
                    firstMessageDate,
                    lastMessageDate,
                    averageTextLength
            );
        }).collect(Collectors.toList());
    }
}
