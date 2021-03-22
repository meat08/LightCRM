package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.ChatMessage;
import ru.lightcrm.utils.MessageStatus;

import java.util.List;

public interface ChatMessageService {
    ChatMessage save(ChatMessage chatMessage);
    Long countNewMessages(Long senderId, Long recipientId);
    List<ChatMessage> findChatMessages(Long senderId, Long recipientId);
    ChatMessage findById(Long id);
    void updateStatuses(List<ChatMessage> messages, Long senderId, Long recipientId, MessageStatus status);
}
