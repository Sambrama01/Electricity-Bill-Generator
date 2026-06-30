public class Billcalculator implements Billoperations {

    // Method to calculate bill using slab rates
    @Override
    public double calculateBill(int units) {

        double amount = 0;

        if (units <= 100) {
            amount = units * 1.5;
        }
        else if (units <= 300) {
            amount = 100 * 1.5 + (units - 100) * 2.5;
        }
        else {
            amount = 100 * 1.5 + 200 * 2.5 + (units - 300) * 4;
        }

        return amount;
    }
}