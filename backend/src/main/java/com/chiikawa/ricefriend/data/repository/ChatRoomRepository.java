package com.chiikawa.ricefriend.data.repository;

import java.util.List;

import com.chiikawa.ricefriend.data.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    @Query(value = "SELECT * FROM chatroom r JOIN chatpart p ON r.id = p.roomid WHERE p.userid = ?1", nativeQuery = true)
    List<ChatRoom> findAllByUserId(int userid);
}