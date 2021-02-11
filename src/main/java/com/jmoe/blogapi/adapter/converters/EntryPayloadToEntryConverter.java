package com.jmoe.blogapi.adapter.converters;

import java.time.LocalDateTime;

import com.jmoe.blogapi.adapter.payload.EntryPayload;
import com.jmoe.blogapi.domain.entity.Entry;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntryPayloadToEntryConverter implements Converter<EntryPayload, Entry> {

    @Synchronized
    @Override
    public Entry convert(EntryPayload entryPayload) {
        return Entry.builder()
                .id(entryPayload.getId())
                .body(entryPayload.getBody())
                .createDate(LocalDateTime.parse(entryPayload.getCreateDate()))
                .updateDate(LocalDateTime.parse(entryPayload.getUpdateDate()))
                .build();
    }
}
