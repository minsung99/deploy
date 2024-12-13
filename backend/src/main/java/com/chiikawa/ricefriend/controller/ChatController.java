package com.chiikawa.ricefriend.controller;

import com.chiikawa.ricefriend.data.dto.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/sub/chatroom/1")
    public MessageDto.MessageResponseDto sendMessage(@Payload MessageDto.MessageResponseDto chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/sub/chatroom/1")
    public MessageDto.MessageResponseDto addUser(@Payload MessageDto.MessageResponseDto chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("user", chatMessage.getUser());
        return chatMessage;
    }
}