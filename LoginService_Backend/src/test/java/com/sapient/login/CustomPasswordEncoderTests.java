package com.sapient.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.login.model.CustomPasswordEncoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomPasswordEncoderTests {

	@Autowired
	CustomPasswordEncoder customPasswordEncoder;

	// Case1:
	// given stored salt n raw password
	// Returns: same hashed password at each run

	@Test
	public void testEncodeWithSaltCase1() throws Exception {
		String rawPassword = "smallWorld";
		String correspondingSalt = "$2a$12$2jDJzTrQ9UOP43LVEyrdwe";
		String expectedHashedPassword = "$2a$12$2jDJzTrQ9UOP43LVEyrdweLBe10SA0csWa5EzsHlQm0suxaWv7UqS";

		String actualHashedPasswordRun1 = customPasswordEncoder.encodeWithSalt(rawPassword, correspondingSalt);

		String actualHashedPasswordRun2 = customPasswordEncoder.encodeWithSalt(rawPassword, correspondingSalt);

		assertEquals(expectedHashedPassword, actualHashedPasswordRun1);
		assertEquals(expectedHashedPassword, actualHashedPasswordRun2);
	}

	// Case2:
	// given different salt rather than stored salt n raw password
	// Returns: different hashed password
	
	@Test
	public void testEncodeWithSaltCase2() throws Exception {
		String rawPassword = "smallWorld";
		String correspondingSalt = "$2a$12$2jDJzTrQ9UOP43LVEyrdsa";
		String expectedHashedPassword = "$2a$12$2jDJzTrQ9UOP43LVEyrdweLBe10SA0csWa5EzsHlQm0suxaWv7UqS";

		String actualHashedPassword = customPasswordEncoder.encodeWithSalt(rawPassword, correspondingSalt);

		assertNotEquals(expectedHashedPassword, actualHashedPassword);
	}
}
