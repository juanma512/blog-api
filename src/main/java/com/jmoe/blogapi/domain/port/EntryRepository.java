package com.jmoe.blogapi.domain.port;

import com.jmoe.blogapi.domain.entity.Entry;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends ReactiveMongoRepository<Entry, String> {

}
