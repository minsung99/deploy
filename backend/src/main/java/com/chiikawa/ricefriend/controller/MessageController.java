package com.chiikawa.ricefriend.controller;

import java.util.List;

import com.chiikawa.ricefriend.data.dto.MessageDto;
import com.chiikawa.ricefriend.data.entity.ChatRoom;
import com.chiikawa.ricefriend.data.entity.User;
import com.chiikawa.ricefriend.service.MessageService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private final MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/addUser")
    public ResponseEntity<String> addUser(@Payload MessageDto.MessageSaveDto messageDto, SimpMessageHeaderAccessor headerAccessor){
        System.out.println("=================== JOIN ===================");

        User user = messageDto.getUser();
        ChatRoom chatroom = messageDto.getChatroom();

        headerAccessor.getSessionAttributes().put("user", user);
        headerAccessor.getSessionAttributes().put("chatroom", chatroom);

        // 메시지 저장
        messageService.saveMessage(messageDto);

        // 메시지를 해당 채팅방 구독자들에게 전송
        messagingTemplate.convertAndSend("/sub/chatroom/" + chatroom.getId(), messageDto);
        return ResponseEntity.ok("메시지 전송 완료");
    }

    @MessageMapping("/leaveUser")
    public ResponseEntity<String> leaveUser(@Payload MessageDto.MessageSaveDto messageDto, SimpMessageHeaderAccessor headerAccessor){
        System.out.println("=================== JOIN ===================");

        ChatRoom chatroom = messageDto.getChatroom();

        // 메시지 저장
        messageService.saveMessage(messageDto);

        // 메시지를 해당 채팅방 구독자들에게 전송
        messagingTemplate.convertAndSend("/sub/chatroom/" + chatroom.getId(), messageDto);
        return ResponseEntity.ok("메시지 전송 완료");
    }

    // 채팅 메시지 수신 및 저장
    @MessageMapping("/message")
    public ResponseEntity<String> receiveMessage(@RequestBody MessageDto.MessageSaveDto messageDto) {
        System.out.println("=================== SENT ===================");

        // 메시지 저장
        messageService.saveMessage(messageDto);

        // 메시지를 해당 채팅방 구독자들에게 전송
        messagingTemplate.convertAndSend("/sub/chatroom/" + messageDto.getChatroom().getId(), messageDto);
        return ResponseEntity.ok("메시지 전송 완료");
    }

    // 메세지 등록
    @PostMapping
    public ResponseEntity<MessageDto.MessageSaveDto> saveMessage(@RequestBody MessageDto.MessageSaveDto requestDto) {
        messageService.saveMessage(requestDto);

        //return ResponseEntity.status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 메세지 조회
    @GetMapping("/{roomid}")
    public ResponseEntity<List<MessageDto.MessageResponseDto>> getMessageListByRoomId(@PathVariable int roomid) {
        List<MessageDto.MessageResponseDto> messageList = messageService.getMessagesByRoomId(roomid);

        return ResponseEntity.ok(messageList);
    }

    @GetMapping("/{userid}/{roomid}")
    public ResponseEntity<List<MessageDto.MessageResponseDto>> getMessageListByCompositeId(@PathVariable int userid, @PathVariable int roomid) {
        List<MessageDto.MessageResponseDto> messageList = messageService.getMessagesByCompositeId(userid, roomid);

        return ResponseEntity.ok(messageList);
    }

    // 전체 메세지 조회
    @GetMapping
    public ResponseEntity<List<MessageDto.MessageResponseDto>> getMessageList() {
        List<MessageDto.MessageResponseDto> messageList = messageService.getAllMessages();

        return ResponseEntity.ok(messageList);
    }
}