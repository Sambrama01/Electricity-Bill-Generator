class Customer {
// Private variables (Encapsulation)
private int customerId;
private String name;
private String address;


// Constructor
public Customer(int customerId, String name, String address) {
    this.customerId = customerId;
    this.name = name;
    this.address = address;
}

// Getter methods
public int getCustomerId() {
    return customerId;
}

public String getName() {
    return name;
}

public String getAddress() {
    return address;
}


}
