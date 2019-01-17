package com.example.restfulinpeace.repository;

import com.example.restfulinpeace.model.Role;
import com.example.restfulinpeace.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}