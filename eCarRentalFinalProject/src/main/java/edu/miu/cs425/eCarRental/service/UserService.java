package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User save(User user);
    User findById(Long uId);
    void delete(Long uId);
}
