package com.jmoe.blogapi.adapter.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EntryPayload {

    private final String id;
    @NotEmpty
    private final String body;
    @NotNull
    private final String createDate;
    @NotNull
    private final String updateDate;

}
