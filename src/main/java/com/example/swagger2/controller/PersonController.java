package com.example.swagger2.controller;

import com.example.swagger2.dto.CreatePersonRequest;
import com.example.swagger2.dto.PersonResponse;
import com.example.swagger2.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Person API", description = "API for managing persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all persons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "Successful response with list of users"),
            @ApiResponse(responseCode = "400",  description = "Bad request"),
            @ApiResponse(responseCode = "200",  description = "Internal server error")
    })
    public List<PersonResponse> getAllPersons() {
        return service.getAllPersons();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a person by Iddd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "User information retrieved successfully"),
            @ApiResponse(responseCode = "400",  description = "Bad request"),
            @ApiResponse(responseCode = "500",  description = "Internal server error")
    })
    public ResponseEntity<PersonResponse> getPerson(
            @Parameter(description = "Unique identifier of the user")
            @PathVariable("id") String id) {
        return ResponseEntity.ok(service.getPersonById(id));
    }


    @PostMapping
    @Operation(summary = "Create a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "User created successfully"),
            @ApiResponse(responseCode = "400",  description = "Bad request"),
            @ApiResponse(responseCode = "500",  description = "Internal server error")
    })
    public ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody CreatePersonRequest request) {
        return ResponseEntity.ok(service.createPerson(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "User updated successfully"),
            @ApiResponse(responseCode = "400",  description = "Bad request"),
            @ApiResponse(responseCode = "500",  description = "Internal server error")
    })
    public ResponseEntity<PersonResponse> updatePerson(
            @Parameter(description = "Unique identifier of the user")
            @PathVariable("id") String id, @Valid @RequestBody CreatePersonRequest request) {
        return ResponseEntity.ok(service.updatePerson(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "User deleted successfully"),
            @ApiResponse(responseCode = "400",  description = "Bad request"),
            @ApiResponse(responseCode = "500",  description = "Internal server error")
    })
    public void deletePerson(
            @Parameter(description = "Unique identifier of the user")
            @PathVariable("id") String id) {
        service.deletePerson(id);
    }

}