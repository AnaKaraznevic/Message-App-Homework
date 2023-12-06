package com.example.messageapp.controller.message;

import com.example.messageapp.service.MessageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public List<MessageResponse> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping
    public void createMessage(@Valid @RequestBody MessageRequest messageRequest) {
        messageService.createMessage(messageRequest.toMessage());
    }
}
