package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "Robert Paternina", "rpaternina", "123"),
            new Customer(2, "Keiry Diaz", "key", "123"),
            new Customer(3, "Samuel Paternina", "spaternina", "123"),
            new Customer(4, "Claudia Yoli", "yoli", "123")
    ));

    @GetMapping("clientes")
    public List<Customer> getCustomers() {
        return customers;
    }

    @GetMapping("/clientes/{ID}")
    public Customer getCliente(@PathVariable int ID){
        for(Customer ides : customers){
            if(ides.getID() == ID){
                return ides;
            }
        }
        return null;
    }
}
