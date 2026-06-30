class Electricitybill {
private int previousReading;
private int currentReading;
private int units;


// Constructor
public Electricitybill(int previousReading, int currentReading) {
    this.previousReading = previousReading;
    this.currentReading = currentReading;

    // Calculate units consumed
    this.units = currentReading - previousReading;
}

// Getter method
public int getUnits() {
    return units;
}


}
