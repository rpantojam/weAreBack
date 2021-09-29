package app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import app.converter.CartStatus;
import app.converter.CartStatusConverter;

@Entity
@Table(name = "carts")
public class Carts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer id;
	
	@NotNull
	@Column(name = "user_id")
	public Integer userId;
	
	@NotNull
	@Column(name = "creation_dt")
	public Date creationDate;
	
	@NotNull
	@Convert(converter = CartStatusConverter.class)
	@Column(name = "status")
	public CartStatus status;
	
}
