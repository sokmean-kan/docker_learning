package com.devop_learning.docker.user.domain.exception

class DuplicateEmailException(email: String) : RuntimeException("User with email already exists: $email")
