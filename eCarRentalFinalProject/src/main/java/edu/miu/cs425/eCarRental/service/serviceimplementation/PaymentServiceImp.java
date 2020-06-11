package edu.miu.cs425.eCarRental.service.serviceimplementation;

import edu.miu.cs425.eCarRental.model.Payment;
import edu.miu.cs425.eCarRental.repository.IPaymentRepository;
import edu.miu.cs425.eCarRental.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("paymentService")
public class PaymentServiceImp implements PaymentService {
	
	private IPaymentRepository paymentRepository;
	
	@Autowired
	public PaymentServiceImp(IPaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public Payment findById(Long paymentId) {
		return paymentRepository.findById(paymentId).orElse(null);
	}

	@Override
	public void delete(Long pId) {
		paymentRepository.deleteById(pId);
		
	}

	@Override
	public Double getTotalPrice(Payment payment) {
		// TODO Auto-generated method stub
	
		return null;
	}

	@Override
	public String count() {
		// TODO Auto-generated method stub
		return null;
	}
}

	
