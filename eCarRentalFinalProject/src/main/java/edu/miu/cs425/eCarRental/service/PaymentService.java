package edu.miu.cs425.eCarRental.service;

import edu.miu.cs425.eCarRental.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> findAll();
    Payment save(Payment payment);
    Payment findById(Long pId);
    void delete(Long pId);
    String count();
    Double getTotalPrice(Payment payment);
}
