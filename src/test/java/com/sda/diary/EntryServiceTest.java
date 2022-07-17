package com.sda.diary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class EntryServiceTest {

    EntryService entryService;

    @BeforeEach
    void setUp() {
        EntryRepository entryRepository = new EntryRepositoryMock();
        entryService = new EntryService(entryRepository);
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
