package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.dto.CartProductsDto;
import app.model.CartProducts;
import app.service.CartProductsService;

@RestController
@RequestMapping("/cartProducts")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class CartProductsController {
	
	@Autowired
	private CartProductsService service;
	
	@PostMapping("/add")
	public CartProducts add(@RequestBody CartProductsDto dto) throws Exception {
		return service.add(dto);
	}
	
}
