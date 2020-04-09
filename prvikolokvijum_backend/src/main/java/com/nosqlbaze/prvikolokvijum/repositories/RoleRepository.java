package com.nosqlbaze.prvikolokvijum.repositories;



import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nosqlbaze.prvikolokvijum.model.ERole;
import com.nosqlbaze.prvikolokvijum.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}