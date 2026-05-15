package com.devop_learning.docker.user.infrastructure.persistence

import com.devop_learning.docker.user.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
	fun existsByEmail(email: String): Boolean

	fun existsByEmailAndIdNot(email: String, id: Long): Boolean
}
