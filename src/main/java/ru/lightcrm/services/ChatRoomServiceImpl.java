package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.ChatRoom;
import ru.lightcrm.entities.dtos.ChatRoomDto;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.repositories.ChatRoomRepository;
import ru.lightcrm.services.interfaces.ChatRoomService;
import ru.lightcrm.services.interfaces.ProfileService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ProfileService profileService;

    @Override
    public Optional<String> getChatId(Long senderId, Long recipientId, boolean createIfNotExist) {
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (!createIfNotExist) {
                        return Optional.empty();
                    }
                    String chatId = String.format("%d_%d", senderId, recipientId);

                    ChatRoom senderRecipient = ChatRoom.builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .recipientName(getRecipientNameFromProfileById(recipientId))
                            .build();

                    ChatRoom recipientSender = ChatRoom.builder()
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .recipientName(getRecipientNameFromProfileById(senderId))
                            .build();

                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }

    @Override
    public List<ChatRoomDto> getChatsDto(Long senderId) {
        return chatRoomRepository.findAllBySenderId(senderId).stream().map(ChatRoomDto::new).collect(Collectors.toList());
    }

    private String getRecipientNameFromProfileById(Long id) {
        ProfileDto profileDto = profileService.findById(id);
        return String.format("%s %s", profileDto.getFirstname(), profileDto.getLastname());
    }
}
