package com.jmoe.blogapi.adapter.controller;

import com.jmoe.blogapi.adapter.converters.EntryPayloadToEntryConverter;
import com.jmoe.blogapi.adapter.converters.EntryToEntryPayloadConverter;
import com.jmoe.blogapi.adapter.payload.CreateEntryPayloadOut;
import com.jmoe.blogapi.adapter.payload.EntryPayload;
import com.jmoe.blogapi.domain.entity.Entry;
import com.jmoe.blogapi.domain.exception.NotFoundException;
import com.jmoe.blogapi.usecase.CreateEntry;
import com.jmoe.blogapi.usecase.DeleteEntry;
import com.jmoe.blogapi.usecase.FindEntry;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EntryController {

    private final FindEntry findEntry;
    private final CreateEntry createEntry;
    private final DeleteEntry deleteEntry;
    private final EntryToEntryPayloadConverter entryToEntryPayloadConverter;
    private final EntryPayloadToEntryConverter entryPayloadToEntryConverter;

    @GetMapping("/entry/{id}")
    public Mono<EntryPayload> findEntry(@PathVariable("id") final String id) {
        return findEntry.findById(id)
            .map(entryToEntryPayloadConverter::convert)
            .switchIfEmpty(Mono.error(new NotFoundException("Entry not found")));
    }

    @GetMapping("/entry")
    public Flux<EntryPayload> findAll() {
        return findEntry.findAll().map(entryToEntryPayloadConverter::convert);
    }

    @PostMapping("/entry")
    public ResponseEntity<Mono<CreateEntryPayloadOut>> createEntry(@Valid @RequestBody final EntryPayload entryPayload) {
        log.debug("Convert & Save payload");
        Mono<Entry> savedEntry = createEntry.createEntry(entryPayloadToEntryConverter.convert(entryPayload));
        log.info("Creation finished");
        return ResponseEntity.ok(savedEntry
            .map(entry -> CreateEntryPayloadOut.builder()
                .id(entry.getId())
                .build()));
    }

    @DeleteMapping("/entry/{id}")
    public ResponseEntity<Mono<Void>> deleteEntry(@PathVariable("id") final String id) {
        return ResponseEntity.ok(deleteEntry.deleteEntry(id));
    }

}
