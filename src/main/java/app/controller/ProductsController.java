package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.model.Products;
import app.service.ProductsService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ProductsController {

	@Autowired
	private ProductsService service;
	
	@GetMapping("/all")
	public List<Products> all() {
		return service.listAll();
	}

	@GetMapping("/get")
	public Products get(@RequestParam(name = "id") int id) {
		return service.getById(id);
	}

	@PostMapping("/register")
	public Products register(@RequestBody Products product) {
		return service.save(product);
	}
	
}
