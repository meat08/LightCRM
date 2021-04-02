package ru.lightcrm.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.lightcrm.entities.ChatMessage;
import ru.lightcrm.entities.ChatRoom;
import ru.lightcrm.entities.dtos.ChatMessageDto;
import ru.lightcrm.repositories.ChatMessageRepository;
import ru.lightcrm.repositories.ChatRoomRepository;
import ru.lightcrm.services.interfaces.ChatMessageService;
import ru.lightcrm.utils.MessageStatus;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class ChatMessageServiceTest {
    @Autowired
    private ChatMessageService chatMessageService;

    @MockBean
    private ChatMessageRepository chatMessageRepository;
    @MockBean
    private ChatRoomRepository chatRoomRepository;

    @Test
    public void countNewMessagesTest() {
        Mockito.doReturn(2L).when(chatMessageRepository)
                .countChatMessageBySenderIdAndRecipientIdAndMessageStatus(1L, 2L, MessageStatus.RECEIVED);

        Long count = chatMessageService.countNewMessages(1L, 2L);
        Assertions.assertEquals(2, count);
    }

    @Test
    public void findChatMessagesTest() {
        Mockito.doReturn(generateMockMessages()).when(chatMessageRepository)
                .findByChatId("1_2");

        Mockito.doReturn(generateMockChatRoom()).when(chatRoomRepository)
                .findBySenderIdAndRecipientId(1L, 2L);

        List<ChatMessageDto> messages = chatMessageService.findChatMessages(1L, 2L);
        Assertions.assertEquals(2, messages.size());
        Assertions.assertNotNull(messages);

    }

    private List<ChatMessage> generateMockMessages() {
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage(1L, "1_2", 1L, 2L, "Bob", "Joe", "Blah blah", OffsetDateTime.now(), MessageStatus.DELIVERED);
        ChatMessage chatMessage1 = new ChatMessage(2L, "2_1", 2L, 1L, "Joe", "Bob", "Yeah, sure!", OffsetDateTime.now(), MessageStatus.DELIVERED);
        messages.add(chatMessage);
        messages.add(chatMessage1);
        return messages;
    }

    private Optional<ChatRoom> generateMockChatRoom() {
        return Optional.of(new ChatRoom(1L, "1_2", 1L, 2L, "Петр Петров"));
    }
}
