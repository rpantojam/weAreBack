package app.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dto.UserDto;
import app.exception.ApiException;
import app.model.User;
import app.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> listAll() {
		return repository.findAll();
	}
	
	public User save(User user) {
        return repository.save(user);
    }

    public User getUser(Integer id) {
        return repository.findById(id).get();
    }

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
    
    public UserDto login(User user) throws Exception {
    	Integer id = repository.findUser(user.userName, user.password);
    	if (id == null) {
    		throw new ApiException("Usuario o contraseña incorrectas");
    	}
    	
    	UserDto us = new UserDto();
    	us.userName = user.userName;
    	us.token = getJWTToken(user.userName); 
    	us.userId = id;
    	return us;
    }
    
    private String getJWTToken(String userName) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(userName)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
				.compact();

		return token;
	}
	
}
