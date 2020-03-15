package com.project.demo.repos;

import com.project.demo.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {     // Позволяет найти полный список полей или по идентификатору
    List<Message> findByTag(String tag);
}
