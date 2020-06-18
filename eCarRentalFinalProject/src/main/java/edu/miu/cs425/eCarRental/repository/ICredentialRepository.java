package edu.miu.cs425.eCarRental.repository;

import edu.miu.cs425.eCarRental.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("credentialRepository")
public interface ICredentialRepository extends JpaRepository<Credential, Long> {
    Optional<Credential> findByUserName(String username);
}
