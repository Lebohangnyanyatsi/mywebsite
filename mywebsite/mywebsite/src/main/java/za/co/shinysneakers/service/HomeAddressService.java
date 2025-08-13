package za.co.shinysneakers.service;

import za.co.shinysneakers.domain.HomeAddress;
import java.util.List;

public interface HomeAddressService {
    HomeAddress create(HomeAddress address);
    HomeAddress read(Long addressId);
    HomeAddress update(HomeAddress address);
    boolean delete(Long addressId);
    List<HomeAddress> getAll();
}