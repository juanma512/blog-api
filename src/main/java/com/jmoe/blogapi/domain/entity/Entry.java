package com.jmoe.blogapi.domain.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Getter
@Builder
public class Entry {

    @MongoId(value = FieldType.OBJECT_ID)
    private final String id;
    private final String body;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

}
