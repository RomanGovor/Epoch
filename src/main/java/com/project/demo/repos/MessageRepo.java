package com.project.demo.repos;

import com.project.demo.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {     // Позволяет найти полный список полей или по идентификатору
    List<Message> findByTag(String tag);
}
