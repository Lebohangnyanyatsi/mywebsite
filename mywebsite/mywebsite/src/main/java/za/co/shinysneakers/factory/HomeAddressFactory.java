package za.co.shinysneakers.factory;

import za.co.shinysneakers.domain.HomeAddress;
import za.co.shinysneakers.util.Helper;

public class HomeAddressFactory {

    public static HomeAddress createHomeAddress(long addressId, String streetNumber, String streetName,
                                                String suburb, String city, String country, String province, int postalCode) {
        if (addressId <= 0 || Helper.isNullOrEmpty(streetNumber) || Helper.isNullOrEmpty(streetName)
                || Helper.isNullOrEmpty(suburb) || Helper.isNullOrEmpty(city)
                || Helper.isNullOrEmpty(country) || postalCode <= 0) {
            return null;
        }

        return new HomeAddress.Builder()
                .setAddessId(addressId)
                .setStreetNumber(streetNumber)
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setCity(city)
                .setCountry(country)
                .setPostalCode(postalCode)
                .build();
    }
}
