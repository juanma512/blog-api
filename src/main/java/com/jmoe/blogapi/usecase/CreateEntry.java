package com.jmoe.blogapi.usecase;

import com.jmoe.blogapi.domain.entity.Entry;
import reactor.core.publisher.Mono;

public interface CreateEntry {

    Mono<Entry> createEntry(Entry entry);

}
