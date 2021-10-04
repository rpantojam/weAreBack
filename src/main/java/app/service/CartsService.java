package app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.converter.CartStatus;
import app.dto.CartDto;
import app.model.Carts;
import app.repository.CartsRepository;

@Service
@Transactional
public class CartsService {

	@Autowired
	private CartsRepository repository;
	
	@Autowired
	private CartProductsService cartProductsService;
	
	public Carts findActiveByUser(int userId) {
		return repository.findAll().stream()
				.filter(cart -> cart.getUserId() == userId)
				.filter(cart -> cart.getStatus() == CartStatus.ACTIVE)
				.findFirst()
				.get();
	}
	
	public CartDto findDtoByUser(int userId) {
		Carts cart = findActiveByUser(userId);
		if (cart == null) {
			cart = new Carts();
			cart.setCreationDate(new Date());
			cart.setUserId(userId);
			cart.setStatus(CartStatus.ACTIVE);
			cart = repository.save(cart);
		}
		
		CartDto result = new CartDto();
		result.cart = cart;
		result.products = cartProductsService.getCartProducts(cart);
		
		return result;
	}
	
}
