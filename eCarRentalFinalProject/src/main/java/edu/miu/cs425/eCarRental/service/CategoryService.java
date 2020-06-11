package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category save(Category category);
    Category findById(Long cId);
    void delete(Long cId);

}
