package com.example.messageapp.service;

import com.example.messageapp.controller.message.MessageResponse;
import com.example.messageapp.model.Message;

import java.util.List;

public interface MessageService {
    List<MessageResponse> getAllMessages();

    void createMessage(Message message);
}
