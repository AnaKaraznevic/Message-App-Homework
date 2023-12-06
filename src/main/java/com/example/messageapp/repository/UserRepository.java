package com.example.messageapp.repository;

import com.example.messageapp.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "SELECT * FROM users WHERE id = :userId", nativeQuery = true)
    Optional<User> findUserById(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<User> getUserByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users (name, email, role) VALUES (:name, :email, :role)", nativeQuery = true)
    int save(@Param("name") String name, @Param("email") String email, @Param("role") String role);

    @Query(value = "SELECT " +
            "u.id AS user_id, " +
            "u.name AS user_name, " +
            "COUNT(m.id) AS number_of_messages, " +
            "MIN(m.post_date) AS first_message_date, " +
            "MAX(m.post_date) AS last_message_date, " +
            "AVG(LENGTH(m.text)) AS average_text_length " +
            "FROM " +
            "users u " +
            "LEFT JOIN " +
            "messages m ON u.id = m.user_id " +
            "GROUP BY " +
            "u.id, u.name", nativeQuery = true)
    List<Object[]> getAllUserMessageStatisticsRaw();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users WHERE id = :userId", nativeQuery = true)
    void deleteUser(@Param("userId") Long userId);
}
