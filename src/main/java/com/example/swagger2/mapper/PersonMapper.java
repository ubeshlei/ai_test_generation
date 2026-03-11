package com.example.swagger2.mapper;

import com.example.swagger2.dto.CreatePersonRequest;
import com.example.swagger2.dto.PersonResponse;
import com.example.swagger2.mongomodel.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person toEntity(CreatePersonRequest dto) {
        Person person = new Person();
        person.setName(dto.getName());
        person.setAge(dto.getAge());
        person.setAddress(dto.getAddress());
        person.setJob(dto.getJob());

        return person;
    }

    public PersonResponse toResponse(Person person) {
        PersonResponse dto = new PersonResponse();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setAge(person.getAge());
        dto.setAddress(person.getAddress());
        dto.setJob(person.getJob());

        return dto;
    }
}
