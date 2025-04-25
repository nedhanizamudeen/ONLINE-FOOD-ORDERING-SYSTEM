import java.awt.*;
import javax.swing.*;

public class Payment extends JFrame {

    private JPanel formPanel;
    private JRadioButton upiBtn, codBtn, cardBtn;
    private ButtonGroup paymentGroup;
    private JButton confirmBtn;

    // Form fields
    private JTextField upiField, cardField;
    private JPasswordField pinField;

    public Payment() {
        setTitle("Payment Page - QuickBite");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel("Select Payment Method", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        add(heading, BorderLayout.NORTH);

        // Payment radio buttons
        upiBtn = new JRadioButton("UPI");
        codBtn = new JRadioButton("Cash On Delivery");
        cardBtn = new JRadioButton("Debit/Credit Card");

        paymentGroup = new ButtonGroup();
        paymentGroup.add(upiBtn);
        paymentGroup.add(codBtn);
        paymentGroup.add(cardBtn);

        JPanel radioPanel = new JPanel(new GridLayout(1, 3));
        radioPanel.add(upiBtn);
        radioPanel.add(codBtn);
        radioPanel.add(cardBtn);
        add(radioPanel, BorderLayout.CENTER);

        // Form panel that changes based on payment method
        formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Payment Details"));
        add(formPanel, BorderLayout.SOUTH);

        // Form fields
        upiField = new JTextField();
        cardField = new JTextField();
        pinField = new JPasswordField();

        // Confirm button
        confirmBtn = new JButton("Confirm Payment");
        add(confirmBtn, BorderLayout.PAGE_END);

        // Listeners
        upiBtn.addActionListener(e -> showUPIFields());
        codBtn.addActionListener(e -> showCODFields());
        cardBtn.addActionListener(e -> showCardFields());

        confirmBtn.addActionListener(e -> processPayment());

        setVisible(true);
    }

    private void showUPIFields() {
        formPanel.removeAll();
        formPanel.add(new JLabel("Enter UPI ID:"));
        formPanel.add(upiField);
        formPanel.revalidate();
        formPanel.repaint();
    }

    private void showCODFields() {
        formPanel.removeAll();
        formPanel.add(new JLabel("No details needed for COD"));
        formPanel.add(new JLabel(""));
        formPanel.revalidate();
        formPanel.repaint();
    }

    private void showCardFields() {
        formPanel.removeAll();
        formPanel.add(new JLabel("Card Number:"));
        formPanel.add(cardField);
        formPanel.add(new JLabel("PIN:"));
        formPanel.add(pinField);
        formPanel.revalidate();
        formPanel.repaint();
    }

    private void processPayment() {
        if (upiBtn.isSelected()) {
            String upi = upiField.getText();
            JOptionPane.showMessageDialog(this, "✅ Paid via UPI: " + upi);
            // TODO: Connect to DB or backend logic for UPI
        } else if (codBtn.isSelected()) {
            JOptionPane.showMessageDialog(this, "✅ Cash on Delivery selected.");
            // TODO: Connect to DB or backend logic for COD
        } else if (cardBtn.isSelected()) {
            String cardNo = cardField.getText();
            String pin = new String(pinField.getPassword());
            JOptionPane.showMessageDialog(this, "✅ Paid with Card: " + cardNo);
            // TODO: Connect to DB or backend logic for Card Payment
        } else {
            JOptionPane.showMessageDialog(this, "❗ Please select a payment method.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Payment());  // FIXED!
    }
}


