package com.project.customer.resource;

import com.project.customer.business.CustomerBusiness;
import com.project.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/customers")
public class CustomerResource {

    private final CustomerBusiness customerBusiness;

    @Autowired
    public CustomerResource(final CustomerBusiness customerBusiness){
        this.customerBusiness = customerBusiness;
    }


    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "cpf", required = false) final String cpf){


        //quando tiver cpf     = findsearch - cpf vai ser diferente de null - cpf != null
        //quando nao tiver cpf = findall    - cpf vai ser null              - cpf == null


        //  logica ? verdade : falso

        String r = cpf == null ? "Nao Existe cpf" : "Ëxiste cpf";
        boolean r2 = cpf == null ? Boolean.TRUE : Boolean.FALSE;
        //cpf == null ? "Nao existe cpf" : Boolean.TRUE; - NAO TEM COMO USAr TERNARIO


        List<Customer> resultado = cpf == null ? customerBusiness.findAll() : customerBusiness.findSerchCpf(cpf);
        return new ResponseEntity<>(resultado, HttpStatus.OK );
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


}
