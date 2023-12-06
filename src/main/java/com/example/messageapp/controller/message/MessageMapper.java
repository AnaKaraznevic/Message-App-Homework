package com.example.messageapp.controller.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageMapper {

    public static List<MessageResponse> mapToMessageResponseList(List<Object[]> messageResponseListRaw) {
        return messageResponseListRaw.stream()
                .map(row -> {
                    var userName = (String) row[0];
                    var text = (String) row[2];
                    var postDate = Optional.ofNullable((Timestamp) row[1])
                            .map(Timestamp::toLocalDateTime)
                            .orElse(null);

                    return new MessageResponse(userName, text, postDate);
                }).collect(Collectors.toList());
    }
}
