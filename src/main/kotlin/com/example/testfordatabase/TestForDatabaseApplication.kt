package com.example.testfordatabase

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.access.SecurityConfig

@SpringBootApplication
class TestForDatabaseApplication{
	@Bean
	fun init(): CommandLineRunner? {
		return CommandLineRunner { evt: Array<String?>? ->
			logger.debug("hallo")
			logger.debug("noch mal hallo")
		}
	}
}
val logger = LoggerFactory.getLogger(TestForDatabaseApplication::class.java)

fun main(args: Array<String>) {
	runApplication<TestForDatabaseApplication>(*args)
}
