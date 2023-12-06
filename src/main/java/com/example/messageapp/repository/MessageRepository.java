package com.example.messageapp.repository;

import com.example.messageapp.model.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT " +
            "u.name, " +
            "m.post_date, " +
            "m.text " +
            "FROM messages m " +
            "JOIN users u ON u.id = m.user_id " +
            "ORDER BY m.post_date DESC", nativeQuery = true)
    List<Object[]> findAllMessagesRaw();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO messages (user_id, text, post_date) VALUES (:userId, :text, :postDate)", nativeQuery = true)
    int save(@Param("userId") Long userId, @Param("text") String name, @Param("postDate") LocalDateTime postDate);

    @Transactional
    @Modifying
    @Query(value = "UPDATE messages SET user_id = :anonymousUserId WHERE user_id = :userId", nativeQuery = true)
    void anonymizeMessagesByUserId(@Param("userId") Long userId, @Param("anonymousUserId") Long anonymousUserId);
}
