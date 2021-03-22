package ru.lightcrm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.lightcrm.controllers.interfaces.ChatController;
import ru.lightcrm.entities.ChatMessage;
import ru.lightcrm.entities.ChatNotification;
import ru.lightcrm.services.interfaces.ChatMessageService;
import ru.lightcrm.services.interfaces.ChatRoomService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatControllerImpl implements ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void processMessage(ChatMessage chatMessage) {
        Optional<String> chatId = chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId().toString(), "/queue/messages",
                new ChatNotification(saved.getId(), saved.getSenderId(), saved.getSenderName())
        );
    }

    @Override
    public ResponseEntity<Long> countMessages(Long senderId,Long recipientId) {
        return ResponseEntity.ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @Override
    public ResponseEntity<?> findChatMessages(Long senderId, Long recipientId) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @Override
    public ResponseEntity<?> findMessage(Long id) {
        return ResponseEntity.ok(chatMessageService.findById(id));
    }
}
