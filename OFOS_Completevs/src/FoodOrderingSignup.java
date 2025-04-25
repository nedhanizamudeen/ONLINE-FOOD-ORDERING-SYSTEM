import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class FoodOrderingSignup {
    private static final Color MAIN_BG_COLOR = new Color(245, 245, 245);
    private static final Color BUTTON_COLOR = new Color(233, 30, 99);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final int FORM_WIDTH = 300;
    private static final String BG_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\flat-lay-arrangement-with-salad-box-sauce.jpg";
    private static final String LOGO_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\logojava.jpg";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FoodOrderingSignup::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Food Ordering - Create Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        BackgroundPanel contentPane = new BackgroundPanel(BG_IMAGE_PATH);
        frame.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
        containerPanel.setBorder(new EmptyBorder(40, 60, 40, 60));
        containerPanel.setOpaque(false);

        JPanel formPanel = createFormPanel();
        JPanel featuresPanel = new JPanel();
        featuresPanel.setOpaque(false);

        containerPanel.add(formPanel);
        containerPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        containerPanel.add(featuresPanel);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);

        contentPane.add(containerPanel, BorderLayout.CENTER);
        contentPane.add(footerPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    backgroundImage = ImageIO.read(imageFile);
                } else {
                    URL resourceURL = FoodOrderingSignup.class.getResource(imagePath);
                    if (resourceURL != null) {
                        backgroundImage = ImageIO.read(resourceURL);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(MAIN_BG_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());

            if (backgroundImage != null) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                g2d.setColor(new Color(150, 150, 150, 100));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }

    private static JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(new ShadowBorder(), new EmptyBorder(20, 20, 20, 20)));
        formPanel.setMaximumSize(new Dimension(FORM_WIDTH, Integer.MAX_VALUE));
        formPanel.setPreferredSize(new Dimension(FORM_WIDTH, 500));

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setBackground(Color.WHITE);
        try {
            ImageIcon logoIcon = new ImageIcon(LOGO_IMAGE_PATH);
            Image img = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logoPanel.add(new JLabel(new ImageIcon(img)));
        } catch (Exception e) {
            JLabel fallback = new JLabel("FoodClub Homepage");
            fallback.setFont(TITLE_FONT);
            fallback.setForeground(BUTTON_COLOR);
            logoPanel.add(fallback);
        }

        JTextField usernameField = createStyledTextField("Enter Username");
        JTextField emailField = createStyledTextField("Enter Email Address");
        JPasswordField passwordField = new JPasswordField();
        passwordField.setText("Password");
        passwordField.setPreferredSize(new Dimension(FORM_WIDTH - 40, 40));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JButton createButton = new JButton("CREATE ACCOUNT");
        createButton.setForeground(Color.WHITE);
        createButton.setBackground(BUTTON_COLOR);
        createButton.setFocusPainted(false);
        createButton.setPreferredSize(new Dimension(FORM_WIDTH - 40, 40));
        createButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        createButton.setOpaque(true);
        createButton.setBorderPainted(false);

        createButton.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("Enter Username") || username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a username", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (email.equals("Enter Email Address") || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an email address", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (password.equals("Password") || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a password", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, "Account created successfully!\nUsername: " + username + "\nEmail: " + email, "Registration Success", JOptionPane.INFORMATION_MESSAGE);

            // Open homepage
            SwingUtilities.invokeLater(() -> FoodOrderingHomepage.main(new String[0]));

            // Close signup window
            Window window = SwingUtilities.getWindowAncestor(createButton);
            if (window != null) window.dispose();
        });

        formPanel.add(logoPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(usernameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(createButton);

        return formPanel;
    }

    private static JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setPreferredSize(new Dimension(FORM_WIDTH - 40, 40));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        return field;
    }

    private static class ShadowBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0, 0, 0, 20));
            for (int i = 0; i < 5; i++) {
                g2d.drawRect(x + i, y + i, width - i * 2 - 1, height - i * 2 - 1);
            }
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 5, 5, 5);
        }
    }
}
