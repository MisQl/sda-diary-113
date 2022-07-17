package com.sda.diary;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EntryService {

    private final EntryRepository entryRepository;
    private final ExternalTimeClient externalTimeClient;

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
        entry.setCreatedDate(externalTimeClient.getTime());

        Entry savedEntry = entryRepository.save(entry);

        return savedEntry;
    }
}
