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
	private Integer id;
	
	@NotNull
	@Column(name = "user_id")
	private Integer userId;
	
	@NotNull
	@Column(name = "creation_dt")
	private Date creationDate;
	
	@NotNull
	@Convert(converter = CartStatusConverter.class)
	@Column(name = "status")
	private CartStatus status;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public CartStatus getStatus() {
		return status;
	}
	
	public void setStatus(CartStatus status) {
		this.status = status;
	}
}
