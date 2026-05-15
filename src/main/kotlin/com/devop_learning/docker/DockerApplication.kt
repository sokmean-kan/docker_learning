package com.devop_learning.docker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerApplication

fun main(args: Array<String>) {
	runApplication<DockerApplication>(*args)
}
