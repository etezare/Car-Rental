package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Booking;
import edu.miu.cs425.eCarRental.model.Category;
import edu.miu.cs425.eCarRental.model.Vehicle;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SearchService {
	
	List<Category> findAvailableCategories(LocalDate start, LocalDate end);
	List<Vehicle> getAvailableVehicles(LocalDate start, LocalDate end);
//	List<Booking> findallByReferenceEquals(String referenceNumber);


}
