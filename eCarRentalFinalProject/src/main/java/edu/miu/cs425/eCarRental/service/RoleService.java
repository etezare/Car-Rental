package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role save(Role role);
    Role findById(Long rId);
    void delete(Long rId);
}
