package za.co.shinysneakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.shinysneakers.domain.HomeAddress;

@Repository
public interface HomeAddressRepository extends JpaRepository<HomeAddress, Long> {
    // You can define custom methods if needed
    // Example: List<HomeAddress> findByCity(String city);
}