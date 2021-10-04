package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dto.CartDto;
import app.service.CartsService;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class CartsController {

	@Autowired
	private CartsService service;
	
	@GetMapping("/getByUser")
	public CartDto getByUser(@RequestParam(name="userId") int userId) {
		return service.findDtoByUser(userId);
	}
	
}
