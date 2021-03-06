package br.com.ionicmelhorlinguagem.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import br.com.ionicmelhorlinguagem.api.model.UserDetailsModel;
import br.com.ionicmelhorlinguagem.api.repository.UserRepository;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class CreateUserTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Test
    @DisplayName("Criando usuarios")
	public void createUser() {
		List<String> roles = new ArrayList<String>();
		roles.add("44444444444");
		String encodedPwd = passwordEncoder.encode("01/01/1990");
		UserDetailsModel user = UserDetailsModel.builder().username("user").password(encodedPwd).roles(roles).build();
		userRepository.save(user);
	}

}
