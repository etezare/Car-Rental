package edu.miu.cs425.eCarRental.repository;

import edu.miu.cs425.eCarRental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Long> {
}
