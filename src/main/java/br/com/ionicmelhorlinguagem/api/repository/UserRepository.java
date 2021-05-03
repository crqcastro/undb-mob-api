package br.com.ionicmelhorlinguagem.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ionicmelhorlinguagem.api.model.UserDetailsModel;

@Repository
public interface UserRepository extends JpaRepository<UserDetailsModel, Long>{

	Optional<UserDetailsModel> findByUsername(String username);

}
