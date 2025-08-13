package za.co.shinysneakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.shinysneakers.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    // Example of a custom query method
    // List<Customer> findByLastName(String lastName);
}
