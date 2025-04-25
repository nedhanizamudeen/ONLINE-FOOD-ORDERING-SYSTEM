import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class CustomerAddressFormWithBackground {
    private static final String BACKGROUND_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\flat-lay-arrangement-with-salad-box-sauce (1).jpg"; // Replace with your background image path

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowAddressForm());
    }

    private static void createAndShowAddressForm() {
        JFrame frame = new JFrame("Enter Address");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);

        // Create a background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel(BACKGROUND_IMAGE_PATH);
        backgroundPanel.setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Enter Your Address", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        backgroundPanel.add(headerLabel, BorderLayout.NORTH);

        // Address form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false); // Transparent to show the background
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Name field
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setForeground(Color.WHITE);
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(400, 30));
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Address line 1
        JLabel addressLine1Label = new JLabel("Address Line 1:");
        addressLine1Label.setFont(new Font("Arial", Font.PLAIN, 16));
        addressLine1Label.setForeground(Color.WHITE);
        JTextField addressLine1Field = new JTextField();
        addressLine1Field.setPreferredSize(new Dimension(400, 30));
        addressLine1Field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Address line 2
        JLabel addressLine2Label = new JLabel("Address Line 2:");
        addressLine2Label.setFont(new Font("Arial", Font.PLAIN, 16));
        addressLine2Label.setForeground(Color.WHITE);
        JTextField addressLine2Field = new JTextField();
        addressLine2Field.setPreferredSize(new Dimension(400, 30));
        addressLine2Field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // City
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        cityLabel.setForeground(Color.WHITE);
        JTextField cityField = new JTextField();
        cityField.setPreferredSize(new Dimension(400, 30));
        cityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // State
        JLabel stateLabel = new JLabel("State:");
        stateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        stateLabel.setForeground(Color.WHITE);
        JTextField stateField = new JTextField();
        stateField.setPreferredSize(new Dimension(400, 30));
        stateField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // ZIP Code
        JLabel zipLabel = new JLabel("ZIP Code:");
        zipLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        zipLabel.setForeground(Color.WHITE);
        JTextField zipField = new JTextField();
        zipField.setPreferredSize(new Dimension(400, 30));
        zipField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Add components to form panel
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(addressLine1Label);
        formPanel.add(addressLine1Field);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(addressLine2Label);
        formPanel.add(addressLine2Field);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(cityLabel);
        formPanel.add(cityField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(stateLabel);
        formPanel.add(stateField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(zipLabel);
        formPanel.add(zipField);

        backgroundPanel.add(formPanel, BorderLayout.CENTER);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(233, 30, 99)); // Pink accent color
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setOpaque(true);
        submitButton.setPreferredSize(new Dimension(100, 40));
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String addressLine1 = addressLine1Field.getText();
            String addressLine2 = addressLine2Field.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String zip = zipField.getText();

            // Validate input
            if (name.isEmpty() || addressLine1.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Save the address to a file
                try (FileWriter writer = new FileWriter("customer_address.txt", true)) {
                    writer.write("Name: " + name + "\n");
                    writer.write("Address Line 1: " + addressLine1 + "\n");
                    writer.write("Address Line 2: " + addressLine2 + "\n");
                    writer.write("City: " + city + "\n");
                    writer.write("State: " + state + "\n");
                    writer.write("ZIP Code: " + zip + "\n");
                    writer.write("-----------------------------\n");
                    JOptionPane.showMessageDialog(frame, "Address saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Close the form
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving address: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(submitButton);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(backgroundPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Custom panel with background image
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.err.println("Could not load background image: " + e.getMessage());
                setBackground(Color.GRAY); // Fallback color
            }
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}