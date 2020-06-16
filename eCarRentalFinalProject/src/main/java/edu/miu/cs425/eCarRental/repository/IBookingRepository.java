package edu.miu.cs425.eCarRental.repository;

import edu.miu.cs425.eCarRental.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("bookingRepository")
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b order by b.bookingDate desc")
    List<Booking> findAllOrderByDate();



}
