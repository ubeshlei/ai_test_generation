package com.example.swagger2.service;

import com.example.swagger2.dto.CreatePersonRequest;
import com.example.swagger2.dto.PersonResponse;
import com.example.swagger2.exception.BadRequestException;
import com.example.swagger2.mapper.PersonMapper;
import com.example.swagger2.mongomodel.Person;
import com.example.swagger2.repo.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;


    public PersonService(PersonRepository repository, PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public PersonResponse createPerson(CreatePersonRequest request) {
        Person person = mapper.toEntity(request);
        Person saved = repository.save(person);
        return mapper.toResponse(saved);
    }

    public List<PersonResponse> getAllPersons() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public PersonResponse getPersonById(String id) {
        try{
            return repository.findById(id)
                    .map(mapper::toResponse)
                    .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid Id format: " + id);
        }

    }

    public PersonResponse updatePerson(String id, CreatePersonRequest request) {
        try {
            return repository.findById(id)
                    .map(person -> {
                        person.setName(request.getName());
                        person.setAge(request.getAge());
                        person.setAddress(request.getAddress());
                        person.setJob(request.getJob());

                        return mapper.toResponse(repository.save(person));
                    })
                    .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid Id format: " + id);
        }

    }

    public void deletePerson(String id) {
        try {
            repository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Person not found with id: " + id);
        }

    }
}
