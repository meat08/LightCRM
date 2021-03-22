package ru.lightcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lightcrm.entities.ChatMessage;
import ru.lightcrm.utils.MessageStatus;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    Long countChatMessageBySenderIdAndRecipientIdAndMessageStatus(Long senderId, Long recipientId, MessageStatus status);
    List<ChatMessage> findByChatId(String chatId);
}
