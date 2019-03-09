package com.example;

import com.example.config.UnitTest;
import com.example.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

import com.example.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
@Category(UnitTest.class)
public class UserRepositoryTests {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    UserRepository userRepository;

    private List<User> testList = new ArrayList<>();
	private User testUser = new User(1, "testUsername", "testPassword");
	private User secondUser = new User(2, "secondUsername", "secondPassword");
	private User nonExistingUser = new User(3, "nonExistingUsername", "nonExistingPassword");

	@Before
	public void setup(){
		testList.add(testUser);
		testList.add(secondUser);
	}

	@Test
	public void getAllUsers_success() {
		doReturn(testList).when(jdbcTemplate).query(anyString(), any(BeanPropertyRowMapper.class));
		String actualListString = userRepository.getAllUsers("Users").toString();
		String testListString = "[User{id=1, username='testUsername', password='testPassword'}, User{id=2, username='secondUsername', password='secondPassword'}]";
		assertEquals(testListString, actualListString);
	}

	@Test
	public void getAllUsers_fail() {
		doReturn(testList).when(jdbcTemplate).query(anyString(), any(BeanPropertyRowMapper.class));
		String actualListString = userRepository.getAllUsers("Users").toString();
		String testListString = "[User{id=1, username='nonExistingUsername', password='nonExistingPassword'}, User{id=2, username='secondUsername', password='secondPassword'}]";
		assertNotEquals(testListString, actualListString);
	}

	@Test
	public void exists_true(){
		if (testList.contains(testUser)) {
			doReturn(true).when(jdbcTemplate).queryForObject(anyString(), eq(Boolean.class));
		} else {
			doReturn(false).when(jdbcTemplate).queryForObject(anyString(), eq(Boolean.class));
		}
		Boolean actual = userRepository.exists("Users", "testUsername", "testPassword");
		assertEquals(true, actual);
	}

	@Test
	public void exists_false(){
		if (testList.contains(nonExistingUser)) {
			doReturn(true).when(jdbcTemplate).queryForObject(anyString(), eq(Boolean.class));
		} else {
			doReturn(false).when(jdbcTemplate).queryForObject(anyString(), eq(Boolean.class));
		}
		Boolean actual = userRepository.exists("Users", "testUsername", "testPassword");
		assertEquals(false, actual);
	}

	@Test
	public void login_true(){
		if (testList.contains(testUser)) {
			doReturn(true).when(jdbcTemplate).queryForObject(anyString(), eq(Boolean.class));
		} else {
			doReturn(false).when(jdbcTemplate).queryForObject(anyString(), eq(Boolean.class));
		}
		Boolean actual = userRepository.login("Users", "testUsername", "testPassword");
		assertEquals(true, actual);
	}

	@Test
	public void login_false(){
		if (testList.contains(nonExistingUser)) {
			doReturn(true).when(jdbcTemplate).queryForObject(anyString(), eq(Boolean.class));
		} else {
			doReturn(false).when(jdbcTemplate).queryForObject(anyString(), eq(Boolean.class));
		}
		Boolean actual = userRepository.login("Users", "nonExistingUsername", "nonExistingPassword");
		assertEquals(false, actual);
	}

	@Test(expected = RuntimeException.class)
	public void addUser_true(){
		if (!testList.contains(nonExistingUser)) {
			userRepository.addUser("Users", "nonExistingUsername", "nonExistingPassword");
		} else {
			assertEquals(true, false);
		}
    }

	@Test(expected = RuntimeException.class)
	public void addUser_false(){
		if (testList.contains(testUser)) {
			userRepository.addUser("Users", "nonExistingUsername", "nonExistingPassword");
		} else {
			assertEquals(true, false);
		}
    }

	@Test(expected = RuntimeException.class)
	public void deleteUser_true(){
		if (testList.contains(testUser)) {
			userRepository.addUser("Users", "nonExistingUsername", "nonExistingPassword");
		} else {
			assertEquals(true, false);
		}
	}

	@Test(expected = RuntimeException.class)
	public void deleteUser_false(){
		if (!testList.contains(nonExistingUser)) {
			userRepository.addUser("Users", "nonExistingUsername", "nonExistingPassword");
		} else {
			assertEquals(true, false);
		}
	}
}
