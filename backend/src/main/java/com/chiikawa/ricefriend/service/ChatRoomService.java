package com.chiikawa.ricefriend.service;

import java.util.List;
import java.util.stream.Collectors;

import com.chiikawa.ricefriend.data.dto.ChatRoomDto;
import com.chiikawa.ricefriend.data.dto.UserDto;
import com.chiikawa.ricefriend.data.entity.ChatRoom;
import com.chiikawa.ricefriend.data.entity.User;
import com.chiikawa.ricefriend.data.repository.ChatRoomRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatroomRepository;

    // 채팅방 생성
    public ChatRoom saveChatRoom(ChatRoomDto.ChatRoomSaveDto requestDto) {
        ChatRoom chatroom = requestDto.toEntity();

        return chatroomRepository.save(chatroom);
    }

    // 채팅방 업데이트
    public void updateChatRoom(int id, ChatRoomDto.ChatRoomUpdateDto requestDto) {
        ChatRoom chatroom = chatroomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id = "+ id + " 채팅방이 없습니다."));

        chatroom.update(requestDto.getName()
                , requestDto.getState()
                , requestDto.getLimitednum());
    }

    // 채팅방 전체 조회
    public List<ChatRoomDto.ChatRoomResponseDto> getAllChatRooms() {
        List<ChatRoom> chatrooms = chatroomRepository.findAll();

        return chatrooms.stream()
                .map(ChatRoomDto.ChatRoomResponseDto::new)
                .collect(Collectors.toList());
    }

    // 유저 아이디를 활용한 채팅방 전체 조회
    public List<ChatRoomDto.ChatRoomResponseDto> getAllChatRoomsByUserId(int userid) {
        List<ChatRoom> chatrooms = chatroomRepository.findAllByUserId(userid);

        return chatrooms.stream()
                .map(ChatRoomDto.ChatRoomResponseDto::new)
                .collect(Collectors.toList());
    }

    // 아이디를 활용한 채팅방 조회
    public ChatRoomDto.ChatRoomResponseDto getChatRoomById(int id) {
        ChatRoom entity = chatroomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방이 없습니다. id=" + id));

        return new ChatRoomDto.ChatRoomResponseDto(entity);
    }

    // 채팅방 삭제
    public void deleteChatRoom(int id) {
        chatroomRepository.deleteById(id);
    }
}