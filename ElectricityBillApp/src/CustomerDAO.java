import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDAO {

    public void save(String name, String address, int units, double bill) {

        try {
            Connection con = DatabaseConnection.getConnection();

            String query = "INSERT INTO customers(name, address, units, bill) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setInt(3, units);
            ps.setDouble(4, bill);

            ps.executeUpdate();

            con.close();

            System.out.println("Data saved successfully!");

        } catch (Exception e) {
            System.out.println("Insert Error: " + e);
        }
    }
}