package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Category;
import edu.miu.cs425.eCarRental.model.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface SearchService {
	
	List<Category> findAvailableCategories(LocalDate start, LocalDate end);
	List<Vehicle> getAvailableVehicles(LocalDate start, LocalDate end);

}
