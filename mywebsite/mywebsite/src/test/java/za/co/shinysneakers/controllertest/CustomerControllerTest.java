package za.co.shinysneakers.controllertest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.co.shinysneakers.controller.CustomerController;
import za.co.shinysneakers.domain.Customer;
import za.co.shinysneakers.factory.CustomerFactory;
import za.co.shinysneakers.service.CustomerService;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {
    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerController controller;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = CustomerFactory.createCustomer("CUST001", "Thapelo", "Sapho",
                "0635233328", "thapelosapho@gmail.com", 1L);
    }

    @Test
    void create() {
        when(service.create(customer)).thenReturn(customer);
        ResponseEntity<Customer> response = controller.create(customer);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void read() {
        when(service.read("CUST001")).thenReturn(customer);
        ResponseEntity<Customer> response = controller.read("CUST001");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void update() {
        when(service.update(customer)).thenReturn(customer);
        ResponseEntity<Customer> response = controller.update(customer);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void deleteSuccess() {
        when(service.delete("CUST001")).thenReturn(true);
        ResponseEntity<Void> response = controller.delete("CUST001");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getAll() {
        List<Customer> customers = Collections.singletonList(customer);
        when(service.getAll()).thenReturn(customers);
        ResponseEntity<List<Customer>> response = controller.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    void exists() {
        when(service.existsByCustomerId("CUST001")).thenReturn(true);
        ResponseEntity<Boolean> response = controller.exists("CUST001");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }
}