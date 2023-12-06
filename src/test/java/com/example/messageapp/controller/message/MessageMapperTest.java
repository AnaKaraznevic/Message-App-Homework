package com.example.messageapp.controller.message;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageMapperTest {
    @Test
    void mapToMessageResponseList_shouldMapRawDataToListOfMessageResponses() {

        Object[] rawMessage = {"Petras Petraitis", Timestamp.valueOf(LocalDateTime.now()), "Labas!"};
        List<Object[]> rawMessages = Collections.singletonList(rawMessage);

        List<MessageResponse> messageResponses = MessageMapper.mapToMessageResponseList(rawMessages);

        assertEquals(1, messageResponses.size());
        assertEquals("Petras Petraitis", messageResponses.get(0).userName());
        assertEquals("Labas!", messageResponses.get(0).text());
    }

}