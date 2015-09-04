package demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * <p/>
 * Copyright &copy; 2006-2015 Watchwith, Inc. The software included herein is property of Watchwith, Inc and its 
 * licensors which reserve all rights, title and interest.
 */
@SpringBootApplication
@RestController
class UiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args)
    }

    @RequestMapping('/resource')
    def home() {
        [id: UUID.randomUUID().toString(), content: "Hiya Worlds"]
    }

}
