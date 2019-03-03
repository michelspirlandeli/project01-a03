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
    public ResponseEntity<?> findAll(@RequestParam("cpf") final String cpf){
       return new ResponseEntity<>(customerBusiness.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id){
        return new  ResponseEntity<>(this.customerBusiness.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody final Customer customer) {
        return new ResponseEntity<>(this.customerBusiness.create(customer),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id, @RequestBody final Customer customer) {
        return new ResponseEntity<>(customerBusiness.update(id, customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        customerBusiness.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //@GetMapping("v1/customers?cpf=")

}
