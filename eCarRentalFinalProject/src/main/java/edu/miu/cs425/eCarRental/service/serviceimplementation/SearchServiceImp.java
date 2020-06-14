package edu.miu.cs425.eCarRental.service.serviceimplementation;


import edu.miu.cs425.eCarRental.model.Booking;
import edu.miu.cs425.eCarRental.model.Category;
import edu.miu.cs425.eCarRental.model.Vehicle;
import edu.miu.cs425.eCarRental.repository.IBookingRepository;
import edu.miu.cs425.eCarRental.repository.IVehicleRepository;
import edu.miu.cs425.eCarRental.service.SearchService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("searchService")
public class SearchServiceImp implements SearchService {
	
	private IVehicleRepository vehicleRepository;
	private IBookingRepository bookingRepository;
	
	public SearchServiceImp(IVehicleRepository vehicleRepository, IBookingRepository bookingRepository) {
		this.vehicleRepository = vehicleRepository;
		this.bookingRepository = bookingRepository;
	}
	
	@Override
	public List<Vehicle> getAvailableVehicles(LocalDate start, LocalDate end){
		List<Vehicle> bookedVehicles = bookingRepository.findAll().stream()
				.filter(b -> (b.getStartDate().isBefore(start) && b.getEndDate().isAfter(start))
								|| b.getStartDate().isAfter(start) && b.getStartDate().isBefore(end) )			
				.map(Booking::getVehicle)
				.collect(Collectors.toList());		
		return vehicleRepository.findAll().stream()
				  				.filter(v -> !bookedVehicles.contains(v))
				  				.collect(Collectors.toList()); 
	}

//	@Override
//	public List<Booking> findallByReferenceEquals(String referenceNumber) {
//		return null;
//	}

//	@Override
//	public Optional<Booking> getVehiclesById(String referenceNumber) {
//		return Optional.empty();
//	}

//	@Override
//	public List<Booking> findallByReferenceEquals(String referenceNumber) {
//		return bookingRepository.findallByReferenceEquals(referenceNumber);
//	}


	@Override
	public List<Category> findAvailableCategories(LocalDate start, LocalDate end) {
		return getAvailableVehicles(start, end).stream()
											   .map(v -> v.getCategory())
											   .distinct()
											   .collect(Collectors.toList());
	}

}
