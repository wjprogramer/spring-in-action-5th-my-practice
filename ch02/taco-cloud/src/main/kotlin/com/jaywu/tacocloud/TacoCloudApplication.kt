package com.jaywu.tacocloud

import org.slf4j.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.*

@SpringBootApplication
class TacoCloudApplication

@Configuration
class LoggingConfiguration {
	@Bean
	@Scope("prototype")
	fun logger(injectionPoint: InjectionPoint): Logger {
		return LoggerFactory.getLogger(
				injectionPoint.methodParameter?.containingClass // constructor
						?: injectionPoint.field?.declaringClass) // or field injection
	}
}

fun main(args: Array<String>) {
	runApplication<TacoCloudApplication>(*args)
}
