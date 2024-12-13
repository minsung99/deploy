package com.chiikawa.ricefriend.data.dto;

import com.chiikawa.ricefriend.data.entity.ChatPart;
import com.chiikawa.ricefriend.data.entity.User;
import com.chiikawa.ricefriend.data.entity.ChatRoom;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Blob;

public class ChatPartDto {
    @Getter
    @Builder
    public static class ChatPartSaveDto{
        private User user;
        private ChatRoom chatroom;

        public ChatPart toEntity() {
            return ChatPart.builder()
                    .user(user)
                    .chatroom(chatroom)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ChatPartResponseDto {
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private User user;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private ChatRoom chatroom;

        public ChatPartResponseDto(ChatPart chatpart) {
            this.user = chatpart.getUser();
            this.chatroom = chatpart.getChatroom();
        }
    }
}