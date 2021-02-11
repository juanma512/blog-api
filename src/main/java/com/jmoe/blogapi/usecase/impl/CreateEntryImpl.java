package com.jmoe.blogapi.usecase.impl;

import com.jmoe.blogapi.domain.entity.Entry;
import com.jmoe.blogapi.domain.port.EntryRepository;
import com.jmoe.blogapi.usecase.CreateEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateEntryImpl implements CreateEntry {

    private final EntryRepository entryRepository;

    @Override
    public Mono<Entry> createEntry(Entry entry) {
        return entryRepository.save(entry);
    }
}
