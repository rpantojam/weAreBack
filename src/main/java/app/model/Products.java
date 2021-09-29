package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer id;
	
	@NotNull
	@Column(name = "name")
	public String name;
	
	@Column(name = "description")
	public String description;
	
	@NotNull
	@Column(name = "cur_price")
	public Integer curPrice;
	
	@NotNull
	@Column(name = "available_amount")
	public Integer availableAmount;
	
}
