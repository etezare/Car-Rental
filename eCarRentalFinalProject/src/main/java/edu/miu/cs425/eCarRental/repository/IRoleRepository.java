package edu.miu.cs425.eCarRental.repository;

import edu.miu.cs425.eCarRental.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("staffRepository")
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
