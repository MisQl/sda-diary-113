package com.sda.diary;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class EntryServiceTest {

    EntryService entryService;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ExternalTimeClient externalTimeClient = new ExternalTimeClient(objectMapper); // todo mock

        EntryRepository entryRepository = new EntryRepositoryMock();
        entryService = new EntryService(entryRepository, externalTimeClient);
    }

    @Test
    void createEntry_whenDataIsCorrect_returnsCorrectEntryObject() {
        // when
        Entry entry = entryService.create("title", "content");

        // then
        assertThat(entry.getId()).isNotNull();
        assertThat(entry.getTitle()).isEqualTo("title");
        assertThat(entry.getContent()).isEqualTo("content");
    }

    @Test
    void createEntry_whenTitleIsNull_throwsAnException() {
        // when
        Throwable throwable = catchThrowable(() -> entryService.create(null, "content"));

        // then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("title");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
