package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAll();
    Vehicle save(Vehicle vehicle);
    Vehicle findById(Long vId);
    void delete(Long vehicleId);
    String assignVehicleNumber();

}
