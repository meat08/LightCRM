package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.ChatMessage;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.ChatMessageRepository;
import ru.lightcrm.services.interfaces.ChatMessageService;
import ru.lightcrm.services.interfaces.ChatRoomService;
import ru.lightcrm.utils.MessageStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setMessageStatus(MessageStatus.RECEIVED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    @Override
    public Long countNewMessages(Long senderId, Long recipientId) {
        return chatMessageRepository.countChatMessageBySenderIdAndRecipientIdAndMessageStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    @Override
    public List<ChatMessage> findChatMessages(Long senderId, Long recipientId) {
        Optional<String> chatId = chatRoomService.getChatId(senderId, recipientId, false);
        List<ChatMessage> messages = chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
        if (messages.size() > 0) {
            updateStatuses(messages, senderId, recipientId, MessageStatus.DELIVERED);
        }
        return messages;
    }

    @Override
    public ChatMessage findById(Long id) {
        return chatMessageRepository.findById(id).map(chatMessage -> {
            chatMessage.setMessageStatus(MessageStatus.DELIVERED);
            return chatMessageRepository.save(chatMessage);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("Сообщение с id %d не найдено", id)));
    }

    @Override
    public void updateStatuses(List<ChatMessage> messages, Long senderId, Long recipientId, MessageStatus status) {
        for (ChatMessage message : messages) {
            if (senderId.equals(message.getSenderId())
                    && recipientId.equals(message.getRecipientId())
                    && message.getMessageStatus() != status) {
                message.setMessageStatus(status);
                chatMessageRepository.save(message);
            }
        }
    }
}
