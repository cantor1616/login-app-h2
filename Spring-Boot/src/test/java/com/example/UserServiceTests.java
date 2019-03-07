//package com.example;
//
//import com.example.Service.UserService;
//import com.example.config.UnitTest;
//import com.example.model.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.experimental.categories.Category;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//@Category(UnitTest.class)
//public class UserServiceTests {
//
//	@Autowired
//	private JdbcTemplate jtm;
//
//	@Before
//	private void setup(){
//		String sql = "INSERT INTO Users(Username, Password) VALUES(\'testUsername\', \'testPassword\')";
//		String sql2 = "INSERT INTO Users(Username, Password) VALUES(\'deleteUsername\', \'deletePassword\')";
//	}
//
//	@Test
//	private void getAllUsers_success() {
//		//expected response
////		doReturn(testList).when(UserService.getAllUsers());
////		List<User> actualList = UserService.getAllUsers();
////		assertEquals(testList, actualList);
////
////		return UserService.getAllUsers();
//	}
//
//	@Test
//	private void exists_true(){
//		//expect true
//		return UserService.exists("testUsername", "testPassword");
//	}
//
//	@Test
//	private void exists_false(){
//		//expect false
//		return UserService.exists("nonExistingTestUsername", "nonExistingTestPassword");
//	}
//
//	@Test
//	private void login_true(){
//		//expect true
//		return UserService.login("testUsername", "testPassword");
//	}
//
//	@Test
//	private void login_false(){
//		//expect false
//		return UserService.login("nonExistingTestUsername", "nonExistingTestPassword");
//	}
//
//	@Test
//	private void addUser_true(){
//		//expect true
//		return UserService.addUser("addUsername", "addPassword");
//	}
//
//	@Test
//	private void addUser_false(){
//		//expect false
//		return UserService.addUser("testUsername", "testPassword");
//	}
//
//	@Test
//	private void deleteUser_true(){
//		//expect true
//		return UserService.deleteUser("deleteUsername", "deletePassword");
//	}
//
//	@Test
//	private void deleteUser_false(){
//		//expect false
//		return UserService.deleteUser("nonExistingTestUsername", "nonExistingTestPassword");
//	}
//
//}
