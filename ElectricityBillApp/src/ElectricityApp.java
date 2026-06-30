import javax.swing.*;
import java.awt.*;

public class ElectricityApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Electricity Bill Generator");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(15);
        JTextField addressField = new JTextField(15);
        JTextField prevField = new JTextField(15);
        JTextField currField = new JTextField(15);

        JTextArea output = new JTextArea(10, 35);
        output.setEditable(false);
        output.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(output);

        JButton button = new JButton("Generate Bill");

        // ---------------- ROW 1 ----------------
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // ---------------- ROW 2 ----------------
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Address:"), gbc);

        gbc.gridx = 1;
        panel.add(addressField, gbc);

        // ---------------- ROW 3 ----------------
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Previous Reading:"), gbc);

        gbc.gridx = 1;
        panel.add(prevField, gbc);

        // ---------------- ROW 4 ----------------
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Current Reading:"), gbc);

        gbc.gridx = 1;
        panel.add(currField, gbc);

        // ---------------- ROW 5 (BUTTON) ----------------
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(button, gbc);

        // ---------------- ROW 6 (OUTPUT) ----------------
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(scroll, gbc);

        frame.add(panel);
        frame.setVisible(true);

        // ================= BUTTON LOGIC (UNCHANGED + VALIDATION) =================
        button.addActionListener(e -> {
            try {

                // INPUTS
                String name = nameField.getText().trim();
                String address = addressField.getText().trim();

                // VALIDATION START
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name cannot be empty!");
                    return;
                }

                if (!name.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(frame, "Name must contain only alphabets!");
                    return;
                }

                if (address.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Address cannot be empty!");
                    return;
                }

                if (prevField.getText().trim().isEmpty() || currField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Reading fields cannot be empty!");
                    return;
                }

                int prev = Integer.parseInt(prevField.getText().trim());
                int curr = Integer.parseInt(currField.getText().trim());

                if (prev < 0 || curr < 0) {
                    JOptionPane.showMessageDialog(frame, "Readings cannot be negative!");
                    return;
                }

                if (curr < prev) {
                    JOptionPane.showMessageDialog(frame, "Current reading cannot be less than previous reading!");
                    return;
                }
                // VALIDATION END

                // BUSINESS LOGIC
                Customer customer = new Customer(1, name, address);
                Electricitybill billObj = new Electricitybill(prev, curr);

                int units = billObj.getUnits();

                Billcalculator calc = new Billcalculator();
                double bill = calc.calculateBill(units);

                // DATABASE SAVE
                CustomerDAO dao = new CustomerDAO();
                dao.save(name, address, units, bill);

                // OUTPUT
                output.setText(
                        "----- Electricity Bill -----\n" +
                        "Name: " + customer.getName() + "\n" +
                        "Address: " + customer.getAddress() + "\n" +
                        "Units Consumed: " + units + "\n" +
                        "Total Bill: ₹" + bill
                );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numeric readings!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Something went wrong!");
            }
        });
    }
}