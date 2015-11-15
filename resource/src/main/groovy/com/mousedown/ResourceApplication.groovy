package com.mousedown

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@EnableResourceServer
class ResourceApplication {

    static void main(String[] args) {
        SpringApplication.run ResourceApplication, args
    }

    @RequestMapping('/')
    def home() {
        [id: UUID.randomUUID().toString(), content: 'Hello World']
    }

}
