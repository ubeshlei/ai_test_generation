package com.example.swagger2.controller;

import com.example.swagger2.dto.CreateOrganizationRequest;
import com.example.swagger2.dto.OrganizationResponse;
import com.example.swagger2.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@Tag(name = "Organization API", description = "API for managing organizations")
public class OrganizationController {

    private final OrganizationService service;

    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all organizations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response with list of organizations"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<OrganizationResponse> getAllOrganizations() {
        return service.getAllOrganizations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an organization by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "Organization information retrieved successfully"),
            @ApiResponse(responseCode = "400",  description = "Bad request"),
            @ApiResponse(responseCode = "500",  description = "Internal server error")
    })
    public OrganizationResponse getOrganization(
            @Parameter(description = "Unique identifier of the organization")
            @PathVariable("id") String id) {
        return service.getOrganizationById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "Organization created successfully"),
            @ApiResponse(responseCode = "400",  description = "Bad request"),
            @ApiResponse(responseCode = "500",  description = "Internal server error")
    })
    public OrganizationResponse createOrganization(@Valid @RequestBody CreateOrganizationRequest request) {
        return service.createOrganization(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "Organization updated successfully"),
            @ApiResponse(responseCode = "400",  description = "Bad request"),
            @ApiResponse(responseCode = "500",  description = "Internal server error")
    })
    public OrganizationResponse updateOrganization(
            @Parameter(description = "Unique identifier of the organization")
            @PathVariable("id") String id,
            @Valid @RequestBody CreateOrganizationRequest request) {
        return service.updateOrganization(id, request);
    }

//    @PutMapping("/{id}")
//    @Operation(summary = "Update an organization")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",  description = "Organization updated successfully"),
//            @ApiResponse(responseCode = "400",  description = "Bad request"),
//            @ApiResponse(responseCode = "500",  description = "Internal server error")
//    })
//    public OrganizationResponse updateOrganization(
//            @Parameter(description = "Unique identifier of the organization")
//            @PathVariable("id") String id,
//            @Valid @RequestBody CreateOrganizationRequest request) {
//        return service.updateOrganization(id, request);
//    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "Organization deleted successfully"),
            @ApiResponse(responseCode = "400",  description = "Bad request"),
            @ApiResponse(responseCode = "500",  description = "Internal server error")
    })
    public void deleteOrganization(
            @Parameter(description = "Unique identifier of the organization")
            @PathVariable("id") String id) {
        service.deleteOrganization(id);
    }

}
