package com.sda.diary;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ExternalTimeClientTest {

    @Test
    void getTime_returnsTime() {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ExternalTimeClient externalTimeClient = new ExternalTimeClient(objectMapper);

        // when
        LocalDateTime result = externalTimeClient.getTime();

        // then
        assertThat(result).isNotNull();
    }
}
