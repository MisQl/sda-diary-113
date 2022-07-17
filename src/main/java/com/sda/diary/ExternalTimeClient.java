package com.sda.diary;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class ExternalTimeClient {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
    private final ObjectMapper objectMapper;

    public LocalDateTime getTime() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://worldclockapi.com/api/json/utc/now"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseJson = httpResponse.body();

            TimeDTO timeDTO = objectMapper.readValue(responseJson, TimeDTO.class);
            String currentDateTime = timeDTO.getCurrentDateTime();
            LocalDateTime localDateTime = LocalDateTime.parse(currentDateTime, dateTimeFormatter);

            return localDateTime; // todo set timezone
        } catch (Exception e) {
            throw new RuntimeException("Pobranie czasu nie powiodło się: " + e.getMessage());
        }
    }
}
