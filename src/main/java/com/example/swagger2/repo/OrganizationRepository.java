package com.example.swagger2.repo;

import com.example.swagger2.mongomodel.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepository extends MongoRepository<Organization, String> {}
