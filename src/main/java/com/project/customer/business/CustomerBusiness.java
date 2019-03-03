package com.project.customer.business;

import com.project.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerBusiness {

       List<Customer> findAll();

       Optional<Customer> findById(Long id);

       Optional<Customer> create(Customer customer);

       Optional<Customer> update(Long id , Customer customer);

       void delete(Long id);
}
