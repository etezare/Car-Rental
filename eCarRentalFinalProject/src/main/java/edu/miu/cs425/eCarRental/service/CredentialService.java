package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Credential;

import java.util.List;

public interface CredentialService {
    List<Credential> findAll();
    Credential save(Credential credential);
    Credential findById(Long cId);
    void delete(Long cId);
}
