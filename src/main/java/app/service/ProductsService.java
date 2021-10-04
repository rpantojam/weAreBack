package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.repository.ProductsRepository;
import app.model.Products;

@Service
@Transactional
public class ProductsService {

	@Autowired
	private ProductsRepository repository;
	
	public List<Products> listAll() {
		return repository.findAll();
	}
	
	public Products getById(int id) {
		return repository.getById(id);
	}
	
	public Products save(Products prod) {
		return repository.save(prod);
	}
	
	public String getProductName(Integer productId) {
		return repository.getById(productId).getName();
	}
}
