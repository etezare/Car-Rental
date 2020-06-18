package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> findAll();
    Payment save(Payment payment);
    Payment findById(Long paymentId);
    void delete(Long paymentId);
    String count();
    Double getTotalPrice(Payment payment);
}
