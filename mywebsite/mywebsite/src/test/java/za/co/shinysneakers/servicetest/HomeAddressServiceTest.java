package za.co.shinysneakers.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.co.shinysneakers.domain.HomeAddress;
import za.co.shinysneakers.factory.HomeAddressFactory;
import za.co.shinysneakers.repository.HomeAddressRepository;
import za.co.shinysneakers.service.impl.HomeAddressServiceImpl;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeAddressServiceTest {
    @Mock
    private HomeAddressRepository repository;

    @InjectMocks
    private HomeAddressServiceImpl service;

    private HomeAddress address;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        address = HomeAddressFactory.createHomeAddress(1L, "123", "Main St",
                "Milnerton", "Cape town", "South Africa", "Western cape", 7441);
    }

    @Test
    void create() {
        when(repository.save(address)).thenReturn(address);
        HomeAddress created = service.create(address);
        assertNotNull(created);
        assertEquals(address, created);
    }

    @Test
    void read() {
        when(repository.findById(1L)).thenReturn(Optional.of(address));
        HomeAddress found = service.read(1L);
        assertNotNull(found);
        assertEquals(address, found);
    }

    @Test
    void update() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(address)).thenReturn(address);
        HomeAddress updated = service.update(address);
        assertNotNull(updated);
        assertEquals(address, updated);
    }

    @Test
    void deleteSuccess() {
        when(repository.existsById(1L)).thenReturn(true);
        boolean result = service.delete(1L);
        assertTrue(result);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteFailure() {
        when(repository.existsById(1L)).thenReturn(false);
        boolean result = service.delete(1L);
        assertFalse(result);
    }
}