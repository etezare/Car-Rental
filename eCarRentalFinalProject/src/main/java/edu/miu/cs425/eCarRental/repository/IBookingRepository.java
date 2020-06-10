package edu.miu.cs425.eCarRental.repository;

import edu.miu.cs425.eCarRental.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bookingRepository")
public interface IBookingRepository extends JpaRepository<Booking, Long> {
}
