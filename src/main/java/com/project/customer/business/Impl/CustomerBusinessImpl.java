package com.project.customer.business.Impl;

import com.project.customer.business.CustomerBusiness;
import com.project.customer.model.Customer;
import com.project.customer.repository.CustomerRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<Customer> findById(@NonNull final Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> create(@NonNull final Customer customer) {
        return Optional.of(customerRepository.save(customer));
    }

    @Override
    public Optional<Customer> update(@NonNull final Long id, @NonNull final Customer customer) {
        customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        customer.setId(id);
        return Optional.of(customerRepository.save(customer));
    }

    @Override
    public void delete(@NonNull final Long id) {
        customerRepository.deleteById(id);
    }
}
