package edu.miu.cs425.eCarRental.service.serviceimplementation;

import edu.miu.cs425.eCarRental.model.User;
import edu.miu.cs425.eCarRental.repository.IUserRepository;
import edu.miu.cs425.eCarRental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {
	@Autowired
	private IUserRepository userRepository;
	

	public UserServiceImp(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long uId) {
		return userRepository.findById(uId).orElse(null);
	}

	@Override
	public void delete(Long uId) {
		userRepository.deleteById(uId);
	}

}
