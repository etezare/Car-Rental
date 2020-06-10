package edu.miu.cs425.eCarRental.repository;

import edu.miu.cs425.eCarRental.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("paymentRepository")
public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
