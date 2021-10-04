package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.model.CartProducts;
public interface CartProductsRepository extends JpaRepository<CartProducts, Integer> {
	
	/*@Query(value = "select c.* from cart_products c where c.cart_id = ?1", nativeQuery = true)
	public List<CartProducts> findByCart(int cartId);*/
	
	/*@Query(value = "select c.* from cart_products c where c.cart_id = ?1 and c.product_id = ?2", nativeQuery = true)
	public CartProducts findByCartAndUser(int cartId, int productId);*/

}
