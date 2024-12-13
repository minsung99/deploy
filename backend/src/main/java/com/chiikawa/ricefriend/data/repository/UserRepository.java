package com.chiikawa.ricefriend.data.repository;

import java.util.List;
import java.util.Optional;

import com.chiikawa.ricefriend.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM user u JOIN chatpart c ON u.id = c.userid WHERE roomid = ?1", nativeQuery = true)
    List<User> findAllByRoomid(int roomid);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user SET name = ?1, favfood_id1 = ?2, favfood_id2 = ?3, favfood_id3 = ?4 WHERE id = ?1", nativeQuery = true)
    void updateById(String name, Integer favfood_id1, Integer favfood_id2, Integer favfood_id3);
}