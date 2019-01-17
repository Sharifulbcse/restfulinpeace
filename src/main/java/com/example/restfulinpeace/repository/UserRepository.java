package com.example.restfulinpeace.repository;

import com.example.restfulinpeace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByMobleOrEmail(String mobile, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByMobile(String mobile);

    Boolean existsByMobile(String mobile);

    Boolean existsByEmail(String email);
}