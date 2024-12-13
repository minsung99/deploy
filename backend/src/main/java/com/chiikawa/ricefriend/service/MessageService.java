package com.chiikawa.ricefriend.service;

import java.util.List;
import java.util.stream.Collectors;

import com.chiikawa.ricefriend.data.dto.MessageDto;
import com.chiikawa.ricefriend.data.entity.Message;
import com.chiikawa.ricefriend.data.repository.MessageRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(MessageDto.MessageSaveDto requestDto) {

        Message message = requestDto.toEntity();

        return messageRepository.save(message);
    }

    public List<MessageDto.MessageResponseDto> getAllMessages() {
        List<Message> messages = messageRepository.findAll();

        return messages.stream()
                .map(MessageDto.MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<MessageDto.MessageResponseDto> getMessagesByRoomId(int roomid) {
        List<Message> messages = messageRepository.findByRoomId(roomid);

        return messages.stream()
                .map(MessageDto.MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<MessageDto.MessageResponseDto> getMessagesByCompositeId(int userid, int roomid) {
        List<Message> messages = messageRepository.findByCompositeId(userid, roomid);

        return messages.stream()
                .map(MessageDto.MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    public MessageDto.MessageResponseDto getMessageById(int id) {
        Message entity = messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 메세지가 없습니다. id=" + id));

        return new MessageDto.MessageResponseDto(entity);
    }

    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }
}