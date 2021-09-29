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
	public Integer id;
	
	@NotNull
	@Column(name = "cart_id")
	public Integer cartId;
	
	@NotNull
	@Column(name = "product_id")
	public Integer productId;
	
	@NotNull
	@Column(name = "product_price")
	public Integer productPrice;
	
	@NotNull
	@Column(name = "product_amount")
	public Integer productAmount;
	
}
