package com.example.swagger2.service;

import com.example.swagger2.dto.CreateOrganizationRequest;
import com.example.swagger2.dto.OrganizationResponse;
import com.example.swagger2.exception.BadRequestException;
import com.example.swagger2.mapper.OrganizationMapper;
import com.example.swagger2.mongomodel.Organization;
import com.example.swagger2.repo.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;

    public OrganizationService(OrganizationRepository repository, OrganizationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<OrganizationResponse> getAllOrganizations() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public OrganizationResponse getOrganizationById(String id) {
        try {
            return repository.findById(id)
                    .map(mapper::toResponse)
                    .orElseThrow(() -> new RuntimeException("Organization not found with id:" + id));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid Id format: " + id);
        }

    }

    public OrganizationResponse createOrganization(CreateOrganizationRequest request) {
        Organization organization = mapper.toEntity(request);
        Organization saved = repository.save(organization);
        return mapper.toResponse(organization);
    }

    public OrganizationResponse updateOrganization(String id, CreateOrganizationRequest request) {
        try {
            return repository.findById(id).
                    map(organization -> {
                        organization.setName(request.getName());
                        organization.setIndustry(request.getIndustry());
                        organization.setAddress(request.getAddress());
                        return mapper.toResponse(repository.save(organization));
                    })
                    .orElseThrow(() -> new RuntimeException("Organization not found!"));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid Id format: " + id);
        }

    }

    public void deleteOrganization(String id) {
        try {
            repository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid Id format: " + id);
        }
    }
}
