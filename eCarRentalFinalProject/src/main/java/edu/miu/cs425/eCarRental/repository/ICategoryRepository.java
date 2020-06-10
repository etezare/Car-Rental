package edu.miu.cs425.eCarRental.repository;

import edu.miu.cs425.eCarRental.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("categoryRepository")
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
