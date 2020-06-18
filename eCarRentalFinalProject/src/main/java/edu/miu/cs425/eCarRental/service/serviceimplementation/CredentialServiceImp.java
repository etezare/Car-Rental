package edu.miu.cs425.eCarRental.service.serviceimplementation;

import edu.miu.cs425.eCarRental.model.Credential;
import edu.miu.cs425.eCarRental.repository.ICredentialRepository;
import edu.miu.cs425.eCarRental.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("credentialService")
public class CredentialServiceImp implements CredentialService {

	private ICredentialRepository credentialRepository;

	@Autowired
	public CredentialServiceImp(ICredentialRepository credentialRepository) {
		this.credentialRepository = credentialRepository;
	}

	@Override
	public List<Credential> findAll() {

		return credentialRepository.findAll();
	}

	@Override
	public Credential save(Credential credential) {

		return credentialRepository.save(credential);
	}

	@Override
	public Credential findById(Long cId) {

		return credentialRepository.findById(cId).orElse(null);
	}

	@Override
	public void delete(Long cId) {
		credentialRepository.deleteById(cId);

	}

}

