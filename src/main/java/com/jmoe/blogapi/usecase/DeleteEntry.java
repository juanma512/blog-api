package com.jmoe.blogapi.usecase;

import reactor.core.publisher.Mono;

public interface DeleteEntry {

    Mono<Void> deleteEntry(String id);

}
