package com.example.messageapp.service.message;

import com.example.messageapp.controller.message.MessageMapper;
import com.example.messageapp.controller.message.MessageResponse;
import com.example.messageapp.model.Message;
import com.example.messageapp.repository.MessageRepository;
import com.example.messageapp.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    public List<MessageResponse> getAllMessages() {
        List<Object[]> rawMessages = messageRepository.findAllMessagesRaw();
        return MessageMapper.mapToMessageResponseList(rawMessages);
    }

    @Override
    public void createMessage(Message message) {
        var user = userService.getUserById(message.getUserId());
        messageRepository.save(user.getId(), message.getText(), message.getPostDate());
    }
}
