package kr.bora.chatv1.domain.repository;

import kr.legossol.borachat.domain.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Long, Message> {

}