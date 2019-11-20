package test.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@SpringBootConfiguration
open class KotlinApplication

    fun main(args: Array<String>) {
        SpringApplication.run(KotlinApplication::class.java, *args)
    }

