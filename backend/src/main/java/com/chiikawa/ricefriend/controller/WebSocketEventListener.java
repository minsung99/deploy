package com.chiikawa.ricefriend.controller;

import com.chiikawa.ricefriend.data.dto.MessageDto;
import com.chiikawa.ricefriend.data.dto.UserDto;
import com.chiikawa.ricefriend.data.entity.ChatRoom;
import com.chiikawa.ricefriend.data.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.chiikawa.ricefriend.model.*;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

//    @EventListener
//    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
//        logger.info("Received a new web socket connection");
//    }

//    @EventListener
//    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        System.out.println(headerAccessor.getSessionAttributes());
//        User user = (User) headerAccessor.getSessionAttributes().get("user");
//        ChatRoom chatroom = (ChatRoom) headerAccessor.getSessionAttributes().get("chatroom");
//
//        System.out.println("=================LEAVE==================");
//
//        if(user != null) {
//            logger.info("User Disconnected : " + user.getName());
//
//            MessageDto.MessageResponseDto chatMessage = new MessageDto.MessageResponseDto();
//            chatMessage.setUser(user);
//            chatMessage.setChatroom(chatroom);
//            chatMessage.setType(MessageType.LEAVE);
//
//            messagingTemplate.convertAndSend("/sub/chatroom/" + chatMessage.getChatroom().getId(), chatMessage);
//        }
//    }
}