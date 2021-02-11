package com.jmoe.blogapi.usecase.impl;

import com.jmoe.blogapi.domain.entity.Entry;
import com.jmoe.blogapi.domain.port.EntryRepository;
import com.jmoe.blogapi.usecase.FindEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public final class FindEntryImpl implements FindEntry {

    private final EntryRepository entryRepository;

    public Mono<Entry> findById(String id) {
        log.debug("Getting entry from repository");
        return entryRepository.findById(id);
    }

    public Flux<Entry> findAll() {
        log.debug("Getting all entries");
        return entryRepository.findAll();
    }

}
