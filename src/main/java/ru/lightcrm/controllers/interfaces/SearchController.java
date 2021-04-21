package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@Api(value = "/api/v1/search", tags = "Контроллер, осуществляющий поиск по индексированному содержимому", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "/api/v1/search", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SearchController {

    @ApiOperation(value = "Возвращает профиль c основными характеристиками по указанному id", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Некорректно сформирован запрос"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ничего не найдено")
    })
    @GetMapping()
    ResponseEntity<?> getSearchResult(@ApiParam(value = "Фраза, по которой осуществляется поиск", name = "searchString", required = true)
                                      @RequestParam(name = "q") @NotNull String searchString);
}
