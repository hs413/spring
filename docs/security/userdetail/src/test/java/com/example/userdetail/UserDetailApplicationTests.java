package com.example.userdetail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserDetailApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void userWhenNotAuthenticated() throws Exception {
		this.mockMvc.perform(get("/user"))
			.andExpect(status().isUnauthorized());
	}

	@Test
	@WithUserDetails("user@example.com")
	void userWhenWithUserDetailsThenOk() throws Exception {
		this.mockMvc.perform(get("/user"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", equalTo(1)));
	}

	@Test
	@WithUser
	void userWhenWithUserThenOk() throws Exception {
		this.mockMvc.perform(get("/user"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", equalTo(1)));
	}

	@Test
	@WithMockCustomUser(email = "admin@example.com")
	void userWhenWithMockCustomUserThenOk() throws Exception {
		this.mockMvc.perform(get("/user"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.email", equalTo("admin@example.com")));
	}

	@Test
	@WithMockCustomUser(email = "admin@example.com")
	void userWhenWithMockCustomAdminThenOk() throws Exception {
		this.mockMvc.perform(get("/user"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.email", equalTo("admin@example.com")));
	}

}
