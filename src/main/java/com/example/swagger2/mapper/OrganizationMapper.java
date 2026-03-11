package com.example.swagger2.mapper;

import com.example.swagger2.dto.CreateOrganizationRequest;
import com.example.swagger2.dto.OrganizationResponse;
import com.example.swagger2.mongomodel.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    public Organization toEntity(CreateOrganizationRequest dto) {
        Organization organization = new Organization();
        organization.setName(dto.getName());
        organization.setIndustry(dto.getIndustry());
        organization.setAddress(dto.getAddress());
        return organization;
    }

    public OrganizationResponse toResponse(Organization organization) {
        OrganizationResponse dto = new OrganizationResponse();
        dto.setId(organization.getId());
        dto.setName(organization.getName());
        dto.setIndustry(organization.getIndustry());
        dto.setAddress(organization.getAddress());
        return dto;
    }
}
