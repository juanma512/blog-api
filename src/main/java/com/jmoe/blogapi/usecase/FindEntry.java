package com.jmoe.blogapi.usecase;

import com.jmoe.blogapi.domain.entity.Entry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindEntry {

    Mono<Entry> findById(String id);
    Flux<Entry> findAll();

}
