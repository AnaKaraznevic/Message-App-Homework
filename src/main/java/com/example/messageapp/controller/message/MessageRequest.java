package com.example.messageapp.controller.message;

import com.example.messageapp.model.Message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageRequest {

    @NotNull
    private Long userId;

    @NotBlank(message = "Text cannot be blank")
    private String text;

    Message toMessage() {
        return new Message(userId, text, LocalDateTime.now());
    }
}
