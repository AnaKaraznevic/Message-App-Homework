package com.example.messageapp.controller.message;

import java.time.LocalDateTime;

public record MessageResponse(String userName, String text, LocalDateTime postDate) {
}


