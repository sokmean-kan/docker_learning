package com.devop_learning.docker.user.api

import com.devop_learning.docker.shared.web.ApiPaths
import com.devop_learning.docker.user.api.dto.UserRequest
import com.devop_learning.docker.user.api.dto.UserResponse
import com.devop_learning.docker.user.api.mapper.UserMapper
import com.devop_learning.docker.user.application.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiPaths.USERS)
class UserController(
	private val userService: UserService,
) {

	@GetMapping
	fun list(): List<UserResponse> =
		UserMapper.toResponse(userService.findAll())

	@GetMapping("/{id}")
	fun get(@PathVariable id: Long): UserResponse =
		UserMapper.toResponse(userService.findById(id))

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun create(@Valid @RequestBody request: UserRequest): UserResponse =
		UserMapper.toResponse(userService.create(UserMapper.toCommand(request)))

	@PutMapping("/{id}")
	fun update(
		@PathVariable id: Long,
		@Valid @RequestBody request: UserRequest,
	): UserResponse =
		UserMapper.toResponse(userService.update(id, UserMapper.toCommand(request)))

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun delete(@PathVariable id: Long) {
		userService.delete(id)
	}
}
