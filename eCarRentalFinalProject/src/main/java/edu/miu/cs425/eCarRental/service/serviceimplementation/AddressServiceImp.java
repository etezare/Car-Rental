package edu.miu.cs425.eCarRental.service.serviceimplementation;

import edu.miu.cs425.eCarRental.model.Address;
import edu.miu.cs425.eCarRental.repository.IAddressRepository;
import edu.miu.cs425.eCarRental.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("addressService")
public class AddressServiceImp implements AddressService {
	
	@Autowired
	private IAddressRepository addressRepository;

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}
	
	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Address findById(Long aId) {
		return addressRepository.findById(aId).orElse(null);
	}

	@Override
	public void delete(Long aId) {
		addressRepository.deleteById(aId);
	}
	
}
