package com.example.messageapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String text;

    private LocalDateTime postDate;

    public Message(Long userId, String text, LocalDateTime postDate) {
        this.userId = userId;
        this.text = text;
        this.postDate = postDate;
    }
}
