package com.chiikawa.ricefriend.data.repository;

import com.chiikawa.ricefriend.data.entity.ChatPart;
import com.chiikawa.ricefriend.data.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query(value = "SELECT * FROM message WHERE  roomid = ?1", nativeQuery = true)
    List<Message> findByRoomId(int roomid);

    @Query(value = "SELECT * FROM message WHERE userid = ?1 AND roomid = ?2", nativeQuery = true)
    List<Message> findByCompositeId(int userid, int roomid);
}