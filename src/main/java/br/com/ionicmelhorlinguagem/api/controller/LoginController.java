package br.com.ionicmelhorlinguagem.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ionicmelhorlinguagem.api.exceptions.BusinessException;
import br.com.ionicmelhorlinguagem.api.exceptions.ExceptionsTypeEnum;
import br.com.ionicmelhorlinguagem.api.model.AuthenticationRequestModel;
import br.com.ionicmelhorlinguagem.api.security.JwtTokenProvider;
import br.com.ionicmelhorlinguagem.api.service.LoginService;

@RestController
@RequestMapping(path = "login")
public class LoginController {

	private JwtTokenProvider jwtTokenProvider;
	private LoginService service;

	@Autowired
	public LoginController(JwtTokenProvider jwtTokenProvider,
			LoginService service) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.service = service;
	}

	@PostMapping
	public ResponseEntity login(@RequestBody AuthenticationRequestModel data, HttpServletRequest request) {
		try {
			String token = jwtTokenProvider.createToken(data.getUsername(),
					this.service.findByUserNameAndPassword(data.getUsername(), data.getPassword())
							.orElseThrow(() -> new BusinessException(ExceptionsTypeEnum.USER_NOT_FOUND)).getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("username", data.getUsername());
			model.put("token", token);
			return ResponseEntity.ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}
	}

}
