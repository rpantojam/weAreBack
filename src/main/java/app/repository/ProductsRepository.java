package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
	
	@Query(value = "select * from products where id = ?1", nativeQuery = true)
	public Products getProduct(int productId);
	
}
