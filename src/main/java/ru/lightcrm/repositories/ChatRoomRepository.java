package ru.lightcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lightcrm.entities.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(Long senderId, Long recipientId);
    List<ChatRoom> findAllBySenderId(Long senderId);
    Optional<ChatRoom> findByChatIdAndRecipientId(String chatId, Long recipientId);
}
