package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "select id from users where user_name = ?1 and password = ?2", nativeQuery = true)
	public Integer findUser(String userName, String password);
	
}