package com.devop_learning.docker.user.domain.exception

class UserNotFoundException(id: Long) : RuntimeException("User not found with id: $id")
