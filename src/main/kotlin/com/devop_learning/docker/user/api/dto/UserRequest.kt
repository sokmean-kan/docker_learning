package com.devop_learning.docker.user.api.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserRequest(
	@field:NotBlank
	@field:Email
	val email: String,

	@field:NotBlank
	@field:Size(max = 255)
	val name: String,
)
