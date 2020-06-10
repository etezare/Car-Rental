package edu.miu.cs425.eCarRental.repository;

import edu.miu.cs425.eCarRental.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("vehicleRepository")
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {
}
