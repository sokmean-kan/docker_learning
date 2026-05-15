package com.devop_learning.docker.user.api

import com.devop_learning.docker.user.domain.exception.DuplicateEmailException
import com.devop_learning.docker.user.domain.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UserExceptionHandler {

	@ExceptionHandler(UserNotFoundException::class)
	fun handleNotFound(ex: UserNotFoundException): ProblemDetail =
		ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.message!!)

	@ExceptionHandler(DuplicateEmailException::class)
	fun handleDuplicateEmail(ex: DuplicateEmailException): ProblemDetail =
		ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.message!!)
}
