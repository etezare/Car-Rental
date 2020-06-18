package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Vehicle;

import java.util.List;

public interface VehicleService {
//    List<Vehicle> findAllByCategoryCategoryId(Long categoryId);
    List<Vehicle> findAll();
    Vehicle save(Vehicle vehicle);
    Vehicle findById(Long vId);
    void delete(Long vId);

    String assignVehicleNumber();

}
