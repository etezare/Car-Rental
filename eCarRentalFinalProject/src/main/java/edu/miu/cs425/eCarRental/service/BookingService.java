package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();
    Booking save(Booking booking);
    Booking findById(Long bId);
    void delete(Long bId);
    String assignReferenceNumber();
}
