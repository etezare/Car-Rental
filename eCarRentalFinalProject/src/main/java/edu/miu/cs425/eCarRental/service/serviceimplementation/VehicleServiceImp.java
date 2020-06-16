package edu.miu.cs425.eCarRental.service.serviceimplementation;

import edu.miu.cs425.eCarRental.model.Vehicle;
import edu.miu.cs425.eCarRental.repository.IVehicleRepository;
import edu.miu.cs425.eCarRental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("vehicleService")
public class VehicleServiceImp implements VehicleService {
	
	private IVehicleRepository vehicleRepository;
	
	@Autowired
	public VehicleServiceImp(IVehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

//	@Override
//	public List<Vehicle> findAllByCategoryCategoryId(Long categoryId) {
//		return vehicleRepository.findAllByCategoryCategoryId(categoryId);
//	}

	@Override
	public List<Vehicle> findAll() {
		return vehicleRepository.findAll();
	}

	@Override
	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@Override
	public Vehicle findById(Long vId) {
		return vehicleRepository.findById(vId).orElse(null);
	}

	@Override
	public void delete(Long vId) {
	vehicleRepository.deleteById(vId);
	}


	@Override
	public String assignVehicleNumber() {
		if(vehicleRepository.findAll().stream().count() == 0) return "VHL1";
		Long currentId = vehicleRepository.findAll().stream().mapToLong(Vehicle::getVehicleId).max().getAsLong();
		return  "VHL" + (currentId + 1) ;
	}

}
