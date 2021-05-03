//package br.com.ionicmelhorlinguagem.api;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import br.com.ionicmelhorlinguagem.api.model.UserDetailsModel;
//import br.com.ionicmelhorlinguagem.api.repository.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class DataInitializer implements CommandLineRunner {
//    
//    
//    private final UserRepository users;
//    
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public DataInitializer(UserRepository users, PasswordEncoder passwordEncoder) {
//		this.users = users;
//		this.passwordEncoder = passwordEncoder;
//	}
//
//    @Override
//    public void run(String... args) {
//        
//        
//        this.users.save(UserDetailsModel.builder()
//                .username("user")
//                .password(this.passwordEncoder.encode("password"))
//                .roles(Arrays.asList("ROLE_USER"))
//                .build()
//        );
//        
//        this.users.save(UserDetailsModel.builder()
//                .username("admin")
//                .password(this.passwordEncoder.encode("password"))
//                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
//                .build()
//        );
//        
//        log.debug("printing all users...");
//        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
//    }
//
//}