package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "Robert Paternina", "rpaternina", "123"),
            new Customer(2, "Keiry Diaz", "key", "123"),
            new Customer(3, "Samuel Paternina", "spaternina", "123"),
            new Customer(4, "Claudia Yoli", "yoli", "123")
    ));


    /**
     * Metodo para obtener todos los clientes
     * ResponseEntity nos sirve para poder manejar los errores HTTP
     * @return
     *
     */
    @GetMapping
    public ResponseEntity <List<Customer>> getCustomers() {
        return ResponseEntity.ok(customers);
    }

    /**
     * Metodo para obtener cliente por ID
     * ResponseEntity<?> sirve para poder retornar otro valor que no sea el asignado en la clase
     * @param id
     * @return
     *
     */

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@GetMapping("/{ID}")
    public ResponseEntity <?> getCliente(@PathVariable int id) {
        for (Customer ides : customers) {
            if (ides.getID() == id) {
                return ResponseEntity.ok(ides);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User no encontrado con el id: " + id);
    }

    /**
     * Metodo para agregar clientes
     * @param customer
     * @return
     */

    //@PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity <?> postCliente(@RequestBody Customer customer) {
        customers.add(customer);

        // Sirve para crear una URI del cliente creado, Osea nos da una ubicacion en URL de donde se creo
        URI ubicacion = ServletUriComponentsBuilder
                // Obtenemos la URI base
                .fromCurrentRequest()
                // Coloco la variable que me permite consumir el recurso por parametro
                .path("/{id}")
                // Toma el valor y lo inserta en la ruta que pase ne path
                .buildAndExpand(customer.getID())
                // Convierte la ruta construida en una URI resultante
                .toUri();


        /** Retorno el codigo HTTP correspondiente con la URI sin mostrar info
         * return ResponseEntity.created(ubicacion).build();
         */

        //Retorna el codigo HTTP correspondiente con la URI con toda la info
        return ResponseEntity.created(ubicacion).body(customer);
    }

    /**
     * Metodo para actualizar cliente, se obtiene por ID
     * @param customer
     * @return
     */

    //@PutMapping
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity <?> putCliente(@RequestBody Customer customer){
        for(Customer clientes : customers){
            if(clientes.getID() == customer.getID()){
                clientes.setID(customer.getID());
                clientes.setName(customer.getName());
                clientes.setUserName(customer.getUserName());
                clientes.setPassword(customer.getPassword());

                // Al actualizar no devuelve nada, solo un 204 exitoso
                return ResponseEntity.noContent().build();
            }
        }
        // Retorna un error de tipo 404
        return ResponseEntity.notFound().build();
    }

    /**
     * Metodo eliminar cliente por ID
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletCliente(@PathVariable int id){
        for (Customer clientes : customers){
            if (clientes.getID() == id){
                customers.remove(clientes);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado con exito " + clientes.getName());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar " + id);
    }

    /**
     * Metodo para actualizar algun dato en especifico y no todos
     * @param customer
     * @return
     */
    @PatchMapping
    public Customer patchCliente(@RequestBody Customer customer){
        for(Customer cliente : customers){
            if(cliente.getID() == customer.getID()){
                if(customer.getName() != null){
                    cliente.setName(customer.getName());
                }if(customer.getUserName() != null){
                    cliente.setUserName(customer.getUserName());
                }if(customer.getPassword() != null){
                    cliente.setPassword(customer.getPassword());
                }
                return cliente;
            }
        }
        return null;
    }
}
