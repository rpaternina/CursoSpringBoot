package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    @GetMapping("/saludo/{name}")
    public String greetin(@PathVariable String name){
        return "Hola " + name;
    }

}
