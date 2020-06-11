package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    Address save(Address address);
    Address findById(Long aId);
    void delete(Long aId);
}
