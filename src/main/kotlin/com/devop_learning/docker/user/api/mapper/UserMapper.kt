package com.devop_learning.docker.user.api.mapper

import com.devop_learning.docker.user.api.dto.UserRequest
import com.devop_learning.docker.user.api.dto.UserResponse
import com.devop_learning.docker.user.application.command.UserCommand
import com.devop_learning.docker.user.domain.model.User

object UserMapper {

	fun toCommand(request: UserRequest): UserCommand =
		UserCommand(email = request.email, name = request.username)

	fun toResponse(user: User): UserResponse =
		UserResponse(
			id = user.id!!,
			email = user.email,
			name = user.name,
			createdAt = user.createdAt,
		)

	fun toResponse(users: List<User>): List<UserResponse> =
		users.map(::toResponse)
}
