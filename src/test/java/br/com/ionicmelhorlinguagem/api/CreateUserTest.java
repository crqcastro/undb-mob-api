package br.com.ionicmelhorlinguagem.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ionicmelhorlinguagem.api.model.UserDetailsModel;
import br.com.ionicmelhorlinguagem.api.repository.UserRepository;

@SpringBootTest
public class CreateUserTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
    @DisplayName("Criando usuarios")
	public void createUser() {
		List<String> roles = new ArrayList<String>();
		roles.add("USER");
		UserDetailsModel user = UserDetailsModel.builder().username("user").password("password").roles(roles).build();
		userRepository.save(user);
	}

}
