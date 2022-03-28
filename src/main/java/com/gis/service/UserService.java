package com.gis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UsersRepository repository;

	@Transactional
	public void addUser() throws Exception {
		User user = new User();
		user.setAddress("Moscow");
		user.setName("Ivan");
//		user.addId(2L);
		final User subSystem = repository.saveAndFlush(user);
		if (1 == 1) {
			throw new Exception("name cannot be null");
		}
	}

}
