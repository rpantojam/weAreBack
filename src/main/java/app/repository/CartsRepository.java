package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.model.Carts;

public interface CartsRepository extends JpaRepository<Carts, Integer> {
	
	@Query(value = "select c.* from carts c where c.user_id = ?1 and c.status = 'A'", nativeQuery = true)
	public Carts findByUser(int userId);
}
