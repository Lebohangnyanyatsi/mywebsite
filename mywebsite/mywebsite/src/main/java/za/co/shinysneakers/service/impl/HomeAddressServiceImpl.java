package za.co.shinysneakers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.shinysneakers.domain.HomeAddress;
import za.co.shinysneakers.repository.HomeAddressRepository;
import za.co.shinysneakers.service.HomeAddressService;
import java.util.List;

@Service
public class HomeAddressServiceImpl implements HomeAddressService {
    private final HomeAddressRepository repository;

    @Autowired
    public HomeAddressServiceImpl(HomeAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public HomeAddress create(HomeAddress address) {
        return repository.save(address);
    }

    @Override
    public HomeAddress read(Long addressId) {
        return repository.findById(addressId).orElse(null);
    }

    @Override
    public HomeAddress update(HomeAddress address) {
        if (repository.existsById(address.getAddressId())) {
            return repository.save(address);
        }
        return null;
    }

    @Override
    public boolean delete(Long addressId) {
        if (repository.existsById(addressId)) {
            repository.deleteById(addressId);
            return true;
        }
        return false;
    }

    @Override
    public List<HomeAddress> getAll() {
        return repository.findAll();
    }
}