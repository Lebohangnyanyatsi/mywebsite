package za.co.shinysneakers.factory;

import za.co.shinysneakers.domain.Customer;
import za.co.shinysneakers.domain.HomeAddress;
import za.co.shinysneakers.util.Helper;

public class CustomerFactory {

    public static Customer createCustomer(String customerId, String firstName, String lastName,
                                          String mobile, String email, long addressId ) {
        if (Helper.isNullOrEmpty(customerId) || Helper.isNullOrEmpty(firstName) || addressId <= 0) {
            return null;
        }

        return new Customer.Builder()
                .setCustomerId(customerId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setMobile(mobile)
                .setEmail(email)
                .setAddressId(addressId)
                .build();
    }
}
