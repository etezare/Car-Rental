package edu.miu.cs425.eCarRental.repository;

import edu.miu.cs425.eCarRental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerRepository")
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
