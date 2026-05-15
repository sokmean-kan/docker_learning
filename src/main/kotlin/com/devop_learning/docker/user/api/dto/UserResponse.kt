package com.devop_learning.docker.user.api.dto

import java.time.Instant

data class UserResponse(
	val id: Long,
	val email: String,
	val name: String,
	val createdAt: Instant,
)
