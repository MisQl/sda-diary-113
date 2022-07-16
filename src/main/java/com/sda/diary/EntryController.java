package com.sda.diary;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EntryController {

    private EntryService entryService = new EntryService();
    private ObjectMapper objectMapper = new ObjectMapper();

    // POST: /entries
    public String createEntry(String json) {
        try {
            EntryDTO entryDTO = objectMapper.readValue(json, EntryDTO.class);
            Entry entry = entryService.create(entryDTO.getTitle(), entryDTO.getContent());
            EntryDTO response = mapToEntryDTO(entry);
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            return String.format("{\"errorMessage\": \"%s\"}", e.getMessage());
        }
    }

    private EntryDTO mapToEntryDTO(Entry entry) {
        // EntryDTO response = new EntryDTO();
        // response.setId(entry.getId());
        // response.setTitle(entry.getTitle());
        // response.setContent(entry.getContent());
        // return response;
        return EntryDTO.builder()
                .id(entry.getId())
                .title(entry.getTitle())
                .content(entry.getContent())
                .build();
    }
}
