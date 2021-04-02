package ru.lightcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.lightcrm.entities.ChatMessage;
import ru.lightcrm.utils.MessageStatus;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    Long countChatMessageBySenderIdAndRecipientIdAndMessageStatus(Long senderId, Long recipientId, MessageStatus status);
    List<ChatMessage> findByChatId(String chatId);
    @Query("SELECT m FROM ChatMessage m WHERE m.timestamp IN " +
            "(SELECT MAX(m.timestamp) FROM ChatMessage m WHERE m.chatId = ?1)")
    Optional<ChatMessage> findByChatIdAndMaxTimestamp(String chatId);
}
