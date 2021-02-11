package com.jmoe.blogapi.usecase.impl;

import com.jmoe.blogapi.domain.port.EntryRepository;
import com.jmoe.blogapi.usecase.DeleteEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteEntryImpl implements DeleteEntry {

    private final EntryRepository entryRepository;

    @Override
    public Mono<Void> deleteEntry(String id) {
        return entryRepository.deleteById(id);
    }
}
