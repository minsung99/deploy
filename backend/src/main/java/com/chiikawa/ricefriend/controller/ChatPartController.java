package com.chiikawa.ricefriend.controller;

import java.util.List;

import com.chiikawa.ricefriend.data.dto.ChatPartDto;
import com.chiikawa.ricefriend.data.entity.ChatPartId;
import com.chiikawa.ricefriend.service.ChatPartService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatparts")
public class ChatPartController {

    private final ChatPartService chatpartService;

    // chatpart 등록
//    @PostMapping
//    public ResponseEntity<ChatPartDto.ChatPartSaveDto> saveChatPart(@RequestBody ChatPartDto.ChatPartSaveDto requestDto) {
//        chatpartService.saveChatPart(requestDto);
//
//        //return ResponseEntity.status(HttpStatus.CREATED).build();
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    // chatpart 등록
    @PostMapping
    public ResponseEntity<ChatPartDto.ChatPartSaveDto> saveChatPartbyCompositeId(@RequestParam int userid, @RequestParam int roomid) {
        System.out.println("====================SAVE====================");
        System.out.println(userid);
        System.out.println(roomid);

        chatpartService.saveChatPartbyCompositeId(userid, roomid);

        //return ResponseEntity.status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // chatpart 조회
    @GetMapping("/{id}")
    public ResponseEntity<ChatPartDto.ChatPartResponseDto> getChatPartById(@PathVariable ChatPartId id) {
        ChatPartDto.ChatPartResponseDto responseDto = chatpartService.getChatPartById(id);

        return ResponseEntity.ok(responseDto);
    }

    // chatpart 조회
    @GetMapping("/{userid}/{roomid}")
    public ResponseEntity<ChatPartDto.ChatPartResponseDto> getChatPartByCompositeId(@PathVariable int userid, @PathVariable int roomid) {
        ChatPartDto.ChatPartResponseDto responseDto = chatpartService.getChatPartByCompositeId(userid, roomid);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 chatpart 조회
    @GetMapping
    public ResponseEntity<List<ChatPartDto.ChatPartResponseDto>> getChatPartList() {
        List<ChatPartDto.ChatPartResponseDto> messageList = chatpartService.getAllChatParts();

        return ResponseEntity.ok(messageList);
    }

    // chatpart 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable ChatPartId id) {
        chatpartService.deleteChatPart(id);
    }

    // chatpart 삭제
    @DeleteMapping("/delete/{userid}/{roomid}")
    public void deleteUserById(@PathVariable int userid, @PathVariable int roomid) {
        chatpartService.deleteChatPart(userid, roomid);
    }
}