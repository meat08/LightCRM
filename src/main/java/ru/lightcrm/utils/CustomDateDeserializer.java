package ru.lightcrm.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomDateDeserializer extends JsonDeserializer<OffsetDateTime> {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String data = jsonParser.getText();
        if (data.contains("+03:00")) {
            return OffsetDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME);
        }
        LocalDateTime localDateTime = null;
        LocalDate localDate = null;
        try {
            localDateTime = LocalDateTime.parse(data, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            localDate = LocalDate.parse(data, dateFormatter);
        }
        if (localDateTime == null) {
            localDateTime = localDate.atStartOfDay();
        }
        ZonedDateTime utcZoned = localDateTime.atZone(ZoneId.systemDefault());
        return utcZoned.toOffsetDateTime();
    }
}