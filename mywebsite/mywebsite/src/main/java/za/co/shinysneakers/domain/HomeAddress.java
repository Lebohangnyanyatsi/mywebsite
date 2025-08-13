package za.co.shinysneakers.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "Home_Address")
public class HomeAddress {
    @Id
    protected long addressId;

    protected String streetNumber;
    protected String streetName;
    protected String suburb;
    protected String city;
    protected String country;
    protected int postalCode;

    protected HomeAddress() {
    }

    public HomeAddress(Builder builder) {
        this.addressId = builder.addressId;
        this.streetNumber = builder.streetNumber;
        this.streetName = builder.streetName;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.country = builder.country;
        this.postalCode = builder.postalCode;
    }

    public long getAddressId() {
        return addressId;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return "HomeAddress{" +
                "addressId=" + addressId +
                ", streetNumber='" + streetNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }

    public static class Builder{

        protected long addressId;
        protected String streetNumber;
        protected String streetName;
        protected String suburb;
        protected String city;
        protected String country;
        protected int postalCode;

        public Builder setAddessId(long addessId) {
            this.addressId = addressId;
            return this;
        }

        public Builder setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public Builder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder setSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setPostalCode(int postalCode) {
            this.postalCode = postalCode;
            return this;
        }
        public Builder copy(){
            this.addressId = this.addressId;
            this.streetNumber = this.streetNumber;
            this.streetName = this.streetName;
            this.suburb = this.suburb;
            this.city = this.city;
            this.country = this.country;
            this.postalCode = this.postalCode;
            return this;
        }

        public HomeAddress build(){
            return this.build();
        }

    }
}
