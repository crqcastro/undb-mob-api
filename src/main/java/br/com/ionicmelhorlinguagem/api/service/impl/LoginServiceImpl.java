package br.com.ionicmelhorlinguagem.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ionicmelhorlinguagem.api.exceptions.BusinessException;
import br.com.ionicmelhorlinguagem.api.exceptions.ExceptionsTypeEnum;
import br.com.ionicmelhorlinguagem.api.model.UserDetailsModel;
import br.com.ionicmelhorlinguagem.api.repository.UserRepository;
import br.com.ionicmelhorlinguagem.api.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public LoginServiceImpl( UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<UserDetailsModel> findByUserNameAndPassword(String username, String password) {
		 Optional<UserDetailsModel> user  = this.userRepository.findByUsername(username);

		 Boolean matches = passwordEncoder.matches(password, user.get().getPassword());

		 if(!matches) throw new BusinessException(ExceptionsTypeEnum.USER_NOT_FOUND);
		 
		 return user;
	}
	
	@Override
	public Optional<UserDetailsModel> findByUserName(String username){
		return this.userRepository.findByUsername(username);
	}

}
