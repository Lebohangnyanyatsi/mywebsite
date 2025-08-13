package za.co.shinysneakers.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    private String customerId;

    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private long addressId;

    protected Customer() {
    }

    private Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.mobile = builder.mobile;
        this.email = builder.email;
        this.addressId = builder.addressId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public long getAddressId() {
        return addressId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", addressId=" + addressId +
                '}';
    }

    public static class Builder {
        private String customerId;
        private String firstName;
        private String lastName;
        private String mobile;
        private String email;
        private long addressId;

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAddressId(long addressId) {
            this.addressId = addressId;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    public Customer copy(Customer customer) {
        this.customerId = customer.customerId;
        this.firstName = customer.firstName;
        this.lastName = customer.lastName;
        this.mobile = customer.mobile;
        this.email = customer.email;
        this.addressId = customer.addressId;
        return this;
    }
}
