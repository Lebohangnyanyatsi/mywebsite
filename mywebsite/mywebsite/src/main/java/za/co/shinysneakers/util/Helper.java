package za.co.shinysneakers.util;

public class Helper {

    // String validation (unchanged)
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    // EDITED: Postal code validation for HomeAddress (4 digits, 1000-9999)
    public static boolean isValidPostalCode(int postalCode) {
        return postalCode >= 1000 && postalCode <= 9999;
    }

    // EDITED: Street number validation for HomeAddress (alphanumeric with 1-5 chars)
    public static boolean isValidStreetNumber(String streetNumber) {
        if (isNullOrEmpty(streetNumber)) return false;
        return streetNumber.matches("^[A-Za-z0-9]{1,5}$");
    }

    // NEW: General string length validation (for names, cities, etc.)
    public static boolean isValidName(String name, int maxLength) {
        if (isNullOrEmpty(name)) return false;
        return name.length() <= maxLength && name.matches("^[a-zA-Z\\s'-]+$");
    }

    // NEW: Email validation for Customer
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) return false;
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    // NEW: Phone number validation for Customer
    public static boolean isValidMobile(String mobile) {
        if (isNullOrEmpty(mobile)) return false;
        // Supports international formats: +27 12 345 6789 or 012 345 6789
        return mobile.matches("^(\\+?\\d{1,3}[- ]?)?\\d{10}$");
    }

    // NEW: ID validation for Customer
    public static boolean isValidCustomerId(String customerId) {
        if (isNullOrEmpty(customerId)) return false;
        return customerId.matches("^[A-Za-z0-9]{6,20}$");
    }

    // NEW: Address component validation (for street, suburb, city, country)
    public static boolean isValidAddressComponent(String component) {
        if (isNullOrEmpty(component)) return false;
        return component.matches("^[a-zA-Z0-9\\s\\-.,'()]{1,50}$");
    }
}