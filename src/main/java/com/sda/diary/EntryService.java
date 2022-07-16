package com.sda.diary;

import java.time.LocalDateTime;

public class EntryService {

    public Entry create(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Pole title nie może być puste");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Pole content nie może być puste");
        }

        Entry entry = new Entry();
        entry.setTitle(title);
        entry.setContent(content);
        entry.setCreatedDate(LocalDateTime.now());

        EntryRepository entryRepository = new EntryRepository();
        Entry savedEntry = entryRepository.save(entry);

        return savedEntry;
    }
}
