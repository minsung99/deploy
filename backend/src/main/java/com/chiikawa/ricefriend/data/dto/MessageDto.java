package com.chiikawa.ricefriend.data.dto;

import java.sql.Timestamp;

import com.chiikawa.ricefriend.data.entity.*;
import com.chiikawa.ricefriend.model.MessageType;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

public class MessageDto {
    @Getter
    @Builder
    public static class MessageSaveDto{
        private User user;
        private ChatRoom chatroom;
        private MessageType type;
        private String detail;
        private Timestamp senttime;

        public Message toEntity() {
            return Message.builder()
                    .user(user)
                    .chatroom(chatroom)
                    .type(type)
                    .detail(detail)
                    .senttime(senttime)
                    .build();
        }
    }

    // ===================요청, 응답 구분선 ================

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageResponseDto {
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private User user;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private ChatRoom chatroom;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private MessageType type;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String detail;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Timestamp senttime;

        public MessageResponseDto(Message message) {
            this.id = message.getId();
            this.user = message.getUser();
            this.chatroom = message.getChatroom();
            this.type = message.getType();
            this.detail = message.getDetail();
            this.senttime = message.getSenttime();
        }
    }
}