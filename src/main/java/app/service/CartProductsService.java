package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dto.CartProductsDto;
import app.dto.CartProductsListDto;
import app.exception.ApiException;
import app.model.CartProducts;
import app.model.Carts;
import app.model.Products;
import app.repository.CartProductsRepository;

@Service
@Transactional
public class CartProductsService {

	@Autowired
	private CartProductsRepository repository;

	@Autowired
	private CartsService cartsService;

	@Autowired
	private ProductsService productService;

	public CartProducts addProductToCart(CartProductsDto dto) throws Exception {
		try {
			CartProducts item = dto.item;
			Carts cart = cartsService.findActiveByUser(dto.userId);
			Products requestedProduct = productService.getById(item.getProductId());
			CartProducts productInCart = findProductInCart(cart.getId(), item.getProductId());
			int requestedAmount = item.getProductAmount();

			if (productInCart != null) {
				requestedAmount += productInCart.getProductAmount();
			}

			if (requestedProduct.getAvailableAmount() < requestedAmount) {
				throw new ApiException("El producto no cuenta con stock suficiente");
			}

			if (productInCart == null) {
				productInCart = new CartProducts();
				productInCart.setCartId(cart.getId());
				productInCart.setProductAmount(item.getProductAmount());
				productInCart.setProductId(item.getProductId());
				productInCart.setProductPrice(item.getProductPrice());
			}

			requestedProduct.setAvailableAmount(requestedProduct.getAvailableAmount() - requestedAmount);
			productService.save(requestedProduct);
			return repository.save(productInCart);
		} catch (Exception e) {
			Logger.getLogger(CartProductsService.class.getName()).log(Level.SEVERE, null, e);
			throw e;
		}
	}

	public List<CartProducts> productsFromCart(int cartId) {
		return repository.findAll().stream().filter(cartProducts -> cartProducts.getCartId() == cartId)
				.collect(Collectors.toList());
	}

	private CartProducts findProductInCart(int cartId, int productId) {
		List<CartProducts> productsFromCart = productsFromCart(cartId);
		Optional<CartProducts> result = productsFromCart.stream()
				.filter(cartProducts -> cartProducts.getProductId() == productId).findFirst();
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	public List<CartProductsListDto> getCartProducts(Carts cart) {
		List<CartProducts> lst = productsFromCart(cart.getId());
		List<CartProductsListDto> lstDto = new ArrayList<>();

		for (int i = 0; i < lst.size(); i++) {
			CartProducts obj = lst.get(i);
			CartProductsListDto l = new CartProductsListDto();
			l.name = productService.getProductName(obj.getProductId());
			l.productAmount = obj.getProductAmount();
			l.productPrice = obj.getProductPrice();
			lstDto.add(l);
		}

		return lstDto;
	}

}
