package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @GetMapping("/hello")
    public String HelloWorld(){
        System.out.println("Ejecutandose");
        return "Hello World";
    }


}
