package com.chiikawa.ricefriend.data.dto;

import com.chiikawa.ricefriend.data.entity.ChatRoom;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ChatRoomDto {
    @Getter
    @Builder
    public static class ChatRoomSaveDto{
        private String name;
        private String state;
        private int limitednum;

        public ChatRoom toEntity() {
            return ChatRoom.builder()
                    .name(name)
                    .state(state)
                    .limitednum(limitednum)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ChatRoomUpdateDto{
        private String name;
        private String state;
        private int limitednum;
    }

    // ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ChatRoomResponseDto{
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String state;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int limitednum;

        public ChatRoomResponseDto(ChatRoom chatroom) {
            this.id = chatroom.getId();
            this.name = chatroom.getName();
            this.state = chatroom.getState();
            this.limitednum = chatroom.getLimitednum();
        }
    }
}