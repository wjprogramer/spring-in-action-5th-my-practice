package com.jaywu.tacocloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * 只要是 Configuration class 皆可以實做 WebMvcConfigurer
 */

@SpringBootApplication
class TacoCloudApplication: WebMvcConfigurer {
	override fun addViewControllers(registry: ViewControllerRegistry) {
		super.addViewControllers(registry)
		registry.addViewController("/").setViewName("home")
	}
}

fun main(args: Array<String>) {
	runApplication<TacoCloudApplication>(*args)
}
