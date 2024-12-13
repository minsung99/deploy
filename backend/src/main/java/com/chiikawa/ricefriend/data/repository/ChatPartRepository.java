package com.chiikawa.ricefriend.data.repository;

import java.util.Optional;

import com.chiikawa.ricefriend.data.entity.ChatPart;
import com.chiikawa.ricefriend.data.entity.ChatPartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ChatPartRepository extends JpaRepository<ChatPart, ChatPartId> {
    @Query(value = "SELECT * FROM chatpart WHERE userid = ?1 AND roomid = ?2", nativeQuery = true)
    Optional<ChatPart> findByCompositeId(int userid, int roomid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO chatpart(userid, roomid) VALUES(?1, ?2)", nativeQuery = true)
    void saveByCompositeId(int userid, int roomid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM chatpart WHERE userid = ?1 AND roomid = ?2", nativeQuery = true)
    void deleteByCompositeId(int userid, int roomid);
}