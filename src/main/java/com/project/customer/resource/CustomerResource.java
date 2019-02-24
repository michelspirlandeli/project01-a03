package com.project.customer.resource;

import com.project.customer.business.CustomerBusiness;
import com.project.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/customers")
public class CustomerResource {


    private final CustomerBusiness customerBusiness;

    @Autowired
    public CustomerResource(final CustomerBusiness customerBusiness){
        this.customerBusiness = customerBusiness;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
       return new ResponseEntity<>(customerBusiness.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="v1/customers/{id}")
    public ResponseEntity<Customer> findById(@PathVariable(value = "id") long id){
        return new  ResponseEntity(this.customerBusiness.customerId(id).get(), HttpStatus.OK);
    }

    //@GetMapping("v1/customers?cpf=")


    @PostMapping(value="v1/customers")
    public ResponseEntity<?> save(@Valid @RequestBody  Customer customer) {
        return new ResponseEntity<>(this.customerBusiness.create(customer),HttpStatus.CREATED);
    }

    @PutMapping(value="v1/customers/{id}")
    public ResponseEntity<?> up(Customer customer) {

        return new ResponseEntity<>(customerBusiness.up(customer), HttpStatus.OK);
    }

//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        return new ResponseEntity<>(customerBusiness.delete(id),HttpStatus.OK);
//    }

    @DeleteMapping(value="v1/customers/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        this.customerBusiness.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
