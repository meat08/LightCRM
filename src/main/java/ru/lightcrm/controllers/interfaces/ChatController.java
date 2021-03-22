package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.lightcrm.entities.ChatMessage;

public interface ChatController {

    @MessageMapping("/chat")
    void processMessage(@Payload ChatMessage chatMessage);

    @ApiOperation(value = "Получить количество сообщений по отправителю и получателю.",
            response = ResponseEntity.class
    )
    @GetMapping(value = "/messages/{senderId}/{recipientId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Long> countMessages(@ApiParam(value = "Идентификатор отправителя", required = true) @PathVariable Long senderId, @ApiParam(value = "Идентификатор получателя", required = true) @PathVariable Long recipientId);

    @ApiOperation(value = "Получить сообщения по отправителю и получателю.",
            response = ResponseEntity.class
    )
    @GetMapping(value = "/messages/{senderId}/{recipientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> findChatMessages(@ApiParam(value = "Идентификатор отправителя", required = true) @PathVariable Long senderId, @ApiParam(value = "Идентификатор получателя", required = true) @PathVariable Long recipientId);

    @ApiOperation(value = "Получить сообщение по id.",
            response = ResponseEntity.class
    )
    @GetMapping(value = "/messages/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> findMessage(@ApiParam(value = "Идентификатор сообщения", required = true) @PathVariable Long id);
}
