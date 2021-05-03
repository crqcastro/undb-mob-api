package br.com.ionicmelhorlinguagem.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ionicmelhorlinguagem.api.model.UserDetailsModel;

@Service
public interface LoginService {

	Optional<UserDetailsModel> findByUserNameAndPassword(String username, String password);
	
	Optional<UserDetailsModel> findByUserName(String username);

}
