package com.example.swagger2.repo;

import com.example.swagger2.mongomodel.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {}
