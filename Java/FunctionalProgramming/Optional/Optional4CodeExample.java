import java.util.Optional;

// Optional-Based Null-Safe Getter Chain (Nested Objects)

class City{
    String cityName;
    City(String cityName) { this.cityName=cityName; }
    public String getCityName() { return this.cityName;}
}

class Address{
    City city;
    Address(City city) { this.city = city; }
    public City getCity() { return this.city; }
}

class Person{
    Address address;
    Person(Address address) { this.address = address; }
    public Address getAddress() { return this.address; }
}

public class Optional4CodeExample {
    public static void main(String[] args) {

        Person p1 = new Person(new Address(new City("Magarpatta")));
        Person p2 = new Person(new Address(null));
        Person p3 = new Person(null);
        Person p4 = null;

        System.out.println("Calling getCityName(p1): "+getCityName(p1));
        // Calling getCityName(p1): Magarpatta

        System.out.println("Calling getCityName(p2): "+getCityName(p2));
        // Calling getCityName(p2): Unknown

        System.out.println("Calling getCityName(p3): "+getCityName(p3));
        // Calling getCityName(p3): Unknown

        System.out.println("Calling getCityName(p4): "+getCityName(p4));
        // Calling getCityName(p4): Unknown
    }

    public static String getCityName(Person person){
        return Optional.ofNullable(person)
        .map(Person::getAddress)    // If person != null → Address, else empty
        .map(Address::getCity)      // If address != null → City, else empty
        .map(City::getCityName)     // If city != null → cityName, else empty
        .orElse("Unknown");  // If anything was null → "Unknown"
    }
}
