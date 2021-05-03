package br.com.ionicmelhorlinguagem.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ionicmelhorlinguagem.api.exceptions.BusinessException;
import br.com.ionicmelhorlinguagem.api.exceptions.ExceptionsTypeEnum;
import br.com.ionicmelhorlinguagem.api.model.UserDetailsModel;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(true)throw new BusinessException(ExceptionsTypeEnum.INVALID_TOKEN_EXCEPTION);
		return null;
	}

}
