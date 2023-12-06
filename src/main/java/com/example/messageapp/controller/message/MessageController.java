package com.example.messageapp.controller.message;

import com.example.messageapp.service.message.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "Get All Messages", description = "Retrieve a list of all messages.")
    @GetMapping
    public List<MessageResponse> getAllMessages() {
        return messageService.getAllMessages();
    }


    @Operation(summary = "Create Message", description = "Create a new message.")@PostMapping
    public void createMessage(@Valid @RequestBody MessageRequest messageRequest) {
        messageService.createMessage(messageRequest.toMessage());
    }
}
