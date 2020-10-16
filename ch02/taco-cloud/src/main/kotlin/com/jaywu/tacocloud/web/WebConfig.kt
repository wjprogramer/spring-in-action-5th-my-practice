package com.jaywu.tacocloud.web

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

    /**
     * 如果有一個 controller 非常簡單，
     * 不需要 fill model 或 process input，
     * 可以使用 `addViewController`，直接轉到 view，而不做其他事情
     */
    override fun addViewControllers(registry: ViewControllerRegistry) {
        super.addViewControllers(registry)
        registry.addViewController("/home").setViewName("home")
    }

}