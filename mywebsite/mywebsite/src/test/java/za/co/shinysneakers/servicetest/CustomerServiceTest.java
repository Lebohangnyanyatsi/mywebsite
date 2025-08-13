package za.co.shinysneakers.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.co.shinysneakers.domain.Customer;
import za.co.shinysneakers.factory.CustomerFactory;
import za.co.shinysneakers.repository.CustomerRepository;
import za.co.shinysneakers.service.impl.CustomerServiceImpl;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerServiceImpl service;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = CustomerFactory.createCustomer("CUST001", "Thapelo", "Sapho",
                "0635233328", "thapelosapho@gmail.com", 1L);
    }

    @Test
    void create() {
        when(repository.save(customer)).thenReturn(customer);
        Customer created = service.create(customer);
        assertNotNull(created);
        assertEquals(customer, created);
    }

    @Test
    void read() {
        when(repository.findById("CUST001")).thenReturn(Optional.of(customer));
        Customer found = service.read("CUST001");
        assertNotNull(found);
        assertEquals(customer, found);
    }

    @Test
    void update() {
        when(repository.existsById("CUST001")).thenReturn(true);
        when(repository.save(customer)).thenReturn(customer);
        Customer updated = service.update(customer);
        assertNotNull(updated);
        assertEquals(customer, updated);
    }

    @Test
    void deleteSuccess() {
        when(repository.existsById("CUST001")).thenReturn(true);
        boolean result = service.delete("CUST001");
        assertTrue(result);
        verify(repository, times(1)).deleteById("CUST001");
    }

    @Test
    void deleteFailure() {
        when(repository.existsById("CUST001")).thenReturn(false);
        boolean result = service.delete("CUST001");
        assertFalse(result);
    }

    @Test
    void existsByCustomerId() {
        when(repository.existsById("CUST001")).thenReturn(true);
        assertTrue(service.existsByCustomerId("CUST001"));
    }
}