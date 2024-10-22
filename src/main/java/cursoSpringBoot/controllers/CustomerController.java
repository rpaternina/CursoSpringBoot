package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.*;

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


    /**
     * Metodo para obtener todos los clientes
     * @return
     */
    @GetMapping("/clientes")
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Metodo para obtener cliente por ID
     * @param ID
     * @return
     */
    @GetMapping("/clientes/{ID}")
    public Customer getCliente(@PathVariable int ID) {
        for (Customer ides : customers) {
            if (ides.getID() == ID) {
                return ides;
            }
        }
        return null;
    }

    /**
     * Metodo para agregar clientes
     * @param customer
     * @return
     */
    @PostMapping("/clientes")
    public Customer postCliente(@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }
}
