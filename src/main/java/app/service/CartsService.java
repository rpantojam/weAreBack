package app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.converter.CartStatus;
import app.dto.CartDto;
import app.dto.CartProductsListDto;
import app.model.CartProducts;
import app.model.Carts;
import app.repository.CartProductsRepository;
import app.repository.CartsRepository;
import app.repository.ProductsRepository;

@Service
@Transactional
public class CartsService {

	@Autowired
	private CartsRepository repository;
	
	@Autowired
	private CartProductsRepository cprepository;
	
	@Autowired
	private ProductsRepository prepository;
	
	public CartDto getByUser(int userId) {
		
		CartDto res = new CartDto(); 
				
		res.cart = repository.findByUser(userId);
		
		if (res.cart == null) {
			Carts c = new Carts();
			c.creationDate = new Date();
			c.userId = userId;
			c.status = CartStatus.ACTIVE;
			res.cart = repository.save(c);
		}
		
		List<CartProducts> lst = cprepository.findByCart(res.cart.id);
		List<CartProductsListDto> lstDto = new ArrayList<>();
		
		for (int i = 0; i < lst.size(); i++) {
			CartProducts obj = lst.get(i);
			CartProductsListDto l = new CartProductsListDto();
			l.name = prepository.getProduct(obj.productId).name;
			l.productAmount = obj.productAmount;
			l.productPrice = obj.productPrice;
			lstDto.add(l);
		}
		
		res.products = lstDto;
		
		return res;
	}
	
}
