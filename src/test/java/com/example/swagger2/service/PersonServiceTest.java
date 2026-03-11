package com.example.swagger2.service;

import com.example.swagger2.dto.CreatePersonRequest;
import com.example.swagger2.dto.PersonResponse;
import com.example.swagger2.mapper.PersonMapper;
import com.example.swagger2.mongomodel.Address;
import com.example.swagger2.mongomodel.Job;
import com.example.swagger2.mongomodel.Person;
import com.example.swagger2.repo.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repo;

    @Mock
    private PersonMapper mapper;

    @InjectMocks
    private PersonService service;

    private Person person;
    private PersonResponse response;
    private CreatePersonRequest request;

    private final Address address = new Address("Lincoln", "New York", "NY5345");
    private final Job job = new Job("Dev", 5500.0, "Tech Corp", "IT");

    @BeforeEach
    void setUp() {
        request = new CreatePersonRequest();
        request.setName("John");
        request.setAge(25);
        request.setAddress(address);
        request.setJob(job);

        person = new Person();
        person.setId("abcdf12345");
        person.setName("John");
        person.setAge(25);
        person.setAddress(address);
        person.setJob(job);

        response = new PersonResponse("abcdf12345", "John", 25, address, job);
    }



    @Test
    void create_shouldReturnSavedPersonWithId() {
        when(mapper.toEntity(request)).thenReturn(person);
        when(repo.save(person)).thenReturn(person);
        when(mapper.toResponse(person)).thenReturn(response);

        PersonResponse result = service.createPerson(request);

        assertThat(result).isEqualTo(response);
        verify(repo).save(person);

    }

    @Test
    void getAllPersons_shouldReturnListOfResponses() {
        when(repo.findAll()).thenReturn(List.of(person));
        when(mapper.toResponse(person)).thenReturn(response);

        List<PersonResponse> result = service.getAllPersons();

        assertThat(result).containsExactly(response);
    }
}
