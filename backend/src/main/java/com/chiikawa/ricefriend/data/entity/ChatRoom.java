package com.chiikawa.ricefriend.data.entity;

import lombok.*;
import jakarta.persistence.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "chatroom")
public class ChatRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    private int id;

    private String name;
    private String state;
    private int limitednum;

    @Builder
    protected ChatRoom(String name, String state, int limitednum) {
        this.name = name;
        this.state = state;
        this.limitednum = limitednum;
    }

    public void update(String name, String state, int limitednum) {
        this.name = name;
        this.state = state;
        this.limitednum = limitednum;
    }
}