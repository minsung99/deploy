package com.chiikawa.ricefriend.data.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ChatPartId implements Serializable {
    private User user;
    private ChatRoom chatroom;
}