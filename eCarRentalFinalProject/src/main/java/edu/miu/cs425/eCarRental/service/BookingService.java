package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookingService {
    List<Booking> findAll();

    Booking save(Booking booking);

    Booking findById(Long bId);

    void delete(Long bId);

    List<Booking> findAllOrderByDate();

    String assignReferenceNumber();

}
