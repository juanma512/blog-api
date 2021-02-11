package com.jmoe.blogapi.adapter.converters;

import com.jmoe.blogapi.adapter.payload.EntryPayload;
import com.jmoe.blogapi.domain.entity.Entry;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntryToEntryPayloadConverter implements Converter<Entry, EntryPayload> {

    @Synchronized
    @Override
    public EntryPayload convert(Entry entry) {
        return EntryPayload.builder()
                .id(entry.getId())
                .body(entry.getBody())
                .createDate(entry.getCreateDate().toString())
                .updateDate(entry.getUpdateDate().toString())
                .build();
    }
}
