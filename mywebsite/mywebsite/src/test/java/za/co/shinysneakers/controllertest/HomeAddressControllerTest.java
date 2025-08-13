package za.co.shinysneakers.controllertest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.co.shinysneakers.controller.HomeAddressController;
import za.co.shinysneakers.domain.HomeAddress;
import za.co.shinysneakers.factory.HomeAddressFactory;
import za.co.shinysneakers.service.HomeAddressService;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeAddressControllerTest {
    @Mock
    private HomeAddressService service;

    @InjectMocks
    private HomeAddressController controller;

    private HomeAddress address;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        address = HomeAddressFactory.createHomeAddress(1L, "123", "Main St",
                "Suburb", "Cape town", "South Africa", "western cape", 7441);
    }

    @Test
    void create() {
        when(service.create(address)).thenReturn(address);
        ResponseEntity<HomeAddress> response = controller.create(address);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(address, response.getBody());
    }

    @Test
    void read() {
        when(service.read(1L)).thenReturn(address);
        ResponseEntity<HomeAddress> response = controller.read(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(address, response.getBody());
    }

    @Test
    void update() {
        when(service.update(address)).thenReturn(address);
        ResponseEntity<HomeAddress> response = controller.update(address);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(address, response.getBody());
    }

    @Test
    void deleteSuccess() {
        when(service.delete(1L)).thenReturn(true);
        ResponseEntity<Void> response = controller.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getAll() {
        List<HomeAddress> addresses = Collections.singletonList(address);
        when(service.getAll()).thenReturn(addresses);
        ResponseEntity<List<HomeAddress>> response = controller.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(addresses, response.getBody());
    }
}