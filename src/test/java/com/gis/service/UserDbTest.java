package com.gis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Assertions;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/mgr-spring-beans-test.xml"})
public class UserDbTest  extends AbstractDatabaseTest{


	@Autowired
	@Qualifier("mgrDataSource")
	private DataSource dataSource;
	@Autowired
	private UserService userService;
	@Autowired
	private UsersRepository usersRepository;


	@Override
	protected DataSource dataSource() {
		return dataSource;
	}

	@BeforeEach
	void setUp() {
		clearDb();
		initDB();
	}

	@Test
	public void test_save_mgr_source()  {
		assertEquals(1, usersRepository.count());
		assertThrows(Exception.class, () -> userService.addUser());
		assertEquals(2, usersRepository.count());
//		assertEquals(1, usersRepository.count());
	}
}
