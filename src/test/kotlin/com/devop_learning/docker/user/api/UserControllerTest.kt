package com.devop_learning.docker.user.api

import com.devop_learning.docker.shared.web.ApiPaths
import com.devop_learning.docker.user.api.dto.UserRequest
import tools.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Autowired
	private lateinit var objectMapper: ObjectMapper

	@Test
	fun list_returnsSeededUsers() {
		mockMvc.perform(get(ApiPaths.USERS))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.length()").value(3))
			.andExpect(jsonPath("$[0].email").value("alice@example.com"))
	}

	@Test
	fun crudLifecycle() {
		val createBody = objectMapper.writeValueAsString(
			UserRequest(email = "dave@example.com", username = "Dave Miller"),
		)

		val createResult = mockMvc.perform(
			post(ApiPaths.USERS)
				.contentType(MediaType.APPLICATION_JSON)
				.content(createBody),
		)
			.andExpect(status().isCreated)
			.andExpect(jsonPath("$.email").value("dave@example.com"))
			.andExpect(jsonPath("$.name").value("Dave Miller"))
			.andReturn()

		val id = objectMapper.readTree(createResult.response.contentAsString).get("id").longValue()

		mockMvc.perform(get("${ApiPaths.USERS}/$id"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.email").value("dave@example.com"))

		val updateBody = objectMapper.writeValueAsString(
			UserRequest(email = "dave@example.com", username = "Dave Updated"),
		)
		mockMvc.perform(
			put("${ApiPaths.USERS}/$id")
				.contentType(MediaType.APPLICATION_JSON)
				.content(updateBody),
		)
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.name").value("Dave Updated"))

		mockMvc.perform(delete("${ApiPaths.USERS}/$id"))
			.andExpect(status().isNoContent)

		mockMvc.perform(get("${ApiPaths.USERS}/$id"))
			.andExpect(status().isNotFound)
	}
}
