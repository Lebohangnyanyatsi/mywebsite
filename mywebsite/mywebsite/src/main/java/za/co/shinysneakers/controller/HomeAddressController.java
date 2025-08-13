package za.co.shinysneakers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.shinysneakers.domain.HomeAddress;
import za.co.shinysneakers.service.HomeAddressService;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class HomeAddressController {
    private final HomeAddressService service;

    @Autowired
    public HomeAddressController(HomeAddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HomeAddress> create(@RequestBody HomeAddress address) {
        HomeAddress created = service.create(address);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<HomeAddress> read(@PathVariable Long addressId) {
        HomeAddress address = service.read(addressId);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }

    @PutMapping
    public ResponseEntity<HomeAddress> update(@RequestBody HomeAddress address) {
        HomeAddress updated = service.update(address);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> delete(@PathVariable Long addressId) {
        if (service.delete(addressId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<HomeAddress>> getAll() {
        List<HomeAddress> addresses = service.getAll();
        return ResponseEntity.ok(addresses);
    }
}