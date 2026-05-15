package com.devop_learning.docker.config

import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.devop_learning.docker.user.infrastructure.persistence"])
@EntityScan(basePackages = ["com.devop_learning.docker.user.domain.model"])
class JpaConfig
