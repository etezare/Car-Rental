package edu.miu.cs425.eCarRental.repository;

import edu.miu.cs425.eCarRental.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("addressRepository")
public interface IAddressRepository extends JpaRepository<Address, Long> {
}
