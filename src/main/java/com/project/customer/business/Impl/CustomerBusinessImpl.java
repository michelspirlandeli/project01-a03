package com.project.customer.business.Impl;

import com.project.customer.business.CustomerBusiness;
import com.project.customer.model.Customer;
import com.project.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerBusinessImpl implements CustomerBusiness {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerBusinessImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> customerId(@PathVariable(value = "id") Long id) {

        return customerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        customerId(id);
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> create(Customer customer) {
            this.customerRepository.save(customer);
            return Optional.of(customer);
    }



    @Override
    public Optional<Customer> up(Customer customer) {
        this.customerRepository.save(customer);
        return Optional.of(customer);
    }
}
