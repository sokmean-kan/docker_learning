package com.devop_learning.docker.user.application

import com.devop_learning.docker.user.application.command.UserCommand
import com.devop_learning.docker.user.domain.exception.DuplicateEmailException
import com.devop_learning.docker.user.domain.exception.UserNotFoundException
import com.devop_learning.docker.user.domain.model.User
import com.devop_learning.docker.user.infrastructure.persistence.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
	private val userRepository: UserRepository,
) {

	@Transactional(readOnly = true)
	fun findAll(): List<User> = userRepository.findAll()

	@Transactional(readOnly = true)
	fun findById(id: Long): User =
		userRepository.findById(id).orElseThrow { UserNotFoundException(id) }

	@Transactional
	fun create(command: UserCommand): User {
		if (userRepository.existsByEmail(command.email)) {
			throw DuplicateEmailException(command.email)
		}
		return userRepository.save(User(email = command.email, name = command.name))
	}

	@Transactional
	fun update(id: Long, command: UserCommand): User {
		val user = userRepository.findById(id).orElseThrow { UserNotFoundException(id) }
		if (userRepository.existsByEmailAndIdNot(command.email, id)) {
			throw DuplicateEmailException(command.email)
		}
		user.email = command.email
		user.name = command.name
		return userRepository.save(user)
	}

	@Transactional
	fun delete(id: Long) {
		if (!userRepository.existsById(id)) {
			throw UserNotFoundException(id)
		}
		userRepository.deleteById(id)
	}
}
