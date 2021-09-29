package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dto.CartProductsDto;
import app.exception.ApiException;
import app.model.CartProducts;
import app.model.Carts;
import app.model.Products;
import app.repository.CartProductsRepository;
import app.repository.CartsRepository;
import app.repository.ProductsRepository;

@Service
@Transactional
public class CartProductsService {

	@Autowired
	private CartProductsRepository repository;
	
	@Autowired
	private CartsRepository crepository;
	
	@Autowired
	private ProductsRepository prepository;
	
	public CartProducts add(CartProductsDto dto) throws Exception {
		CartProducts item = dto.item;
		Carts cart = crepository.findByUser(dto.userId);
		Products p = prepository.getProduct(item.productId);
		CartProducts prod = repository.findByCartAndUser(cart.id, item.productId);
		int requestedAmount = item.productAmount;
		if (prod == null) {
			prod = new CartProducts();
			prod.cartId = cart.id;
			prod.productAmount = item.productAmount;
			prod.productId = item.productId;
			prod.productPrice = item.productPrice;
		} else {
			requestedAmount = item.productAmount + prod.productAmount;
		}
		
		if (p.availableAmount < requestedAmount) {
			throw new ApiException("El producto no cuenta con stock suficiente");
		}
		
		if (prod.id != null) {
			prod.productAmount = requestedAmount;
		}
		
		p.availableAmount = p.availableAmount - requestedAmount;
		prepository.save(p);
		
		return repository.save(prod);
	}
	
}
