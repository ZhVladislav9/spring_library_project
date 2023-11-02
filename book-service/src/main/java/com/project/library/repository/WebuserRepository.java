package com.project.library.repository;

import com.project.library.models.Webuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WebuserRepository extends JpaRepository<Webuser, UUID> {

    Optional<Webuser> findByUsername(String username);

}