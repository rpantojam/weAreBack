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

import app.dto.UserDto;
import app.model.User;
import app.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/all")
	public List<User> all() {
		return service.listAll();
	}

	@GetMapping("/get")
	public User get(@RequestParam(name = "id") int id) {
		return service.getUser(id);
	}

	@PostMapping("/register")
	public String register(@RequestBody User user) {
		service.save(user);
		return "Registrado con éxito";
	}

	@PostMapping("/login")
	public UserDto login(@RequestBody User user) throws Exception {
		return service.login(user);
	}
}
