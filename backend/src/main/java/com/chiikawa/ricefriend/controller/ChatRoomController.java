package com.chiikawa.ricefriend.controller;

import java.util.List;

import com.chiikawa.ricefriend.data.dto.ChatRoomDto;
import com.chiikawa.ricefriend.data.entity.ChatRoom;
import com.chiikawa.ricefriend.service.ChatRoomService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatrooms")
public class ChatRoomController {

    private final ChatRoomService chatroomService;

    // 채팅방 등록
    @PostMapping
    public ResponseEntity<ChatRoom> saveChatRoom(@RequestBody ChatRoomDto.ChatRoomSaveDto requestDto) {
        ChatRoom chatroom = chatroomService.saveChatRoom(requestDto);

        //return ResponseEntity.status(HttpStatus.CREATED).build();
        System.out.println(HttpStatus.CREATED);
        return new ResponseEntity<>(chatroom, HttpStatus.CREATED);
    }

    // 채팅방 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ChatRoomDto.ChatRoomUpdateDto> updateChatRoom(@PathVariable int id, @RequestBody ChatRoomDto.ChatRoomUpdateDto requestDto) {
        chatroomService.updateChatRoom(id, requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 전체 채팅방 조회
    @GetMapping
    public ResponseEntity<List<ChatRoomDto.ChatRoomResponseDto>> getRoomList() {
        List<ChatRoomDto.ChatRoomResponseDto> roomList = chatroomService.getAllChatRooms();

        return ResponseEntity.ok(roomList);
    }

    // 유저 아이디를 활용한 채팅방 전체 조회
    @GetMapping("/chatroom/{userid}")
    public ResponseEntity<List<ChatRoomDto.ChatRoomResponseDto>> getChatRoomByUserId(@PathVariable int userid) {
        List<ChatRoomDto.ChatRoomResponseDto> roomList = chatroomService.getAllChatRoomsByUserId(userid);

        return ResponseEntity.ok(roomList);
    }

    // 채팅방 조회
    @GetMapping("/{id}")
    public ResponseEntity<ChatRoomDto.ChatRoomResponseDto> getChatRoomById(@PathVariable int id) {
        ChatRoomDto.ChatRoomResponseDto responseDto = chatroomService.getChatRoomById(id);

        return ResponseEntity.ok(responseDto);
    }

    // 채팅방 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteChatRoomById(@PathVariable int id) {
        chatroomService.deleteChatRoom(id);
    }
}