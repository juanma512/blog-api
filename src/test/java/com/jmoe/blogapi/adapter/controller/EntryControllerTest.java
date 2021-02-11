package com.jmoe.blogapi.adapter.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

import com.jmoe.blogapi.BlogApiApplication;
import java.time.LocalDateTime;

import com.jmoe.blogapi.domain.entity.Entry;
import com.jmoe.blogapi.domain.port.EntryRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(classes = BlogApiApplication.class)
class EntryControllerTest {

    @Autowired
    ApplicationContext context;

    @MockBean
    EntryRepository entryRepository;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClient.bindToApplicationContext(context)
            .configureClient().baseUrl("https://localhost:8080/")
            .filter(documentationConfiguration(restDocumentation))
            .build();
    }

    @Test
    void findEntry() {
        given(entryRepository.findById(anyString())).willReturn(Mono.just(Entry.builder()
            .id(new ObjectId().toString())
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build()));

        this.webTestClient.get().uri("/entry/{entryId}", new ObjectId()).exchange()
            .expectStatus().isOk().expectBody()
            .consumeWith(document("entry",
                pathParameters(
                    parameterWithName("entryId").description("Entry id")
                ),
                responseFields(
                    fieldWithPath("id").description("Entry id"),
                    fieldWithPath("body").description("Entry content"),
                    fieldWithPath("createDate").description("Creation date"),
                    fieldWithPath("updateDate").description("Update date")
                )
            ));
    }

    @Test
    void findAll() {
        given(entryRepository.findAll()).willReturn(Flux.just());

        this.webTestClient.get().uri("/entry").exchange()
            .expectStatus().isOk().expectBody()
            .consumeWith(document("entryList"));
    }

    @Test
    void createEntry() {
    }

    @Test
    void deleteEntry() {
    }
}