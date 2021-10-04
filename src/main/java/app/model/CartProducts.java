package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cart_products")
public class CartProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name = "cart_id")
	private Integer cartId;
	
	@NotNull
	@Column(name = "product_id")
	private Integer productId;
	
	@NotNull
	@Column(name = "product_price")
	private Integer productPrice;
	
	@NotNull
	@Column(name = "product_amount")
	private Integer productAmount;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCartId() {
		return cartId;
	}
	
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	
	public Integer getProductAmount() {
		return productAmount;
	}
	
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
	
}
