package za.co.shinysneakers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.shinysneakers.domain.Customer;
import za.co.shinysneakers.repository.CustomerRepository;
import za.co.shinysneakers.service.CustomerService;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer read(String customerId) {
        return repository.findById(customerId).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        if (repository.existsById(customer.getCustomerId())) {
            return repository.save(customer);
        }
        return null;
    }

    @Override
    public boolean delete(String customerId) {
        if (repository.existsById(customerId)) {
            repository.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsByCustomerId(String customerId) {
        return repository.existsById(customerId);
    }
}