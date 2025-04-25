import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class FoodOrderingLogin {
    private static final Color MAIN_BG_COLOR = new Color(245, 245, 245);
    private static final Color BUTTON_COLOR = new Color(233, 30, 99); // Pink color from image
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font SUBTITLE_FONT = new Font("Arial", Font.PLAIN, 16);
    private static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final int FORM_WIDTH = 300;
    
    // Background image path - Replace with your actual image path
    private static final String BG_IMAGE_PATH = "\"C:\\Users\\laxmi sah\\Desktop\\onlinefoodorderingsystem\\pictures\\javapictures\\salad.jpeg\"";
    
    // Logo image path - Replace with your actual logo image path
    private static final String LOGO_IMAGE_PATH = "\"C:\\Users\\laxmi sah\\Desktop\\onlinefoodorderingsystem\\pictures\\javapictures\\logojava.jpg\"";
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    
    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame("Food Ordering - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        
        // Set the content pane to our background panel
        BackgroundPanel contentPane = new BackgroundPanel(BG_IMAGE_PATH);
        frame.setContentPane(contentPane);
        
        // Use BorderLayout for the main layout
        contentPane.setLayout(new BorderLayout());
        
        // Create a container panel for the main content
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
        containerPanel.setBorder(new EmptyBorder(40, 60, 40, 60));
        containerPanel.setOpaque(false); // Important! Make it transparent
        
        // Left panel - Form section
        JPanel formPanel = createFormPanel(frame);
        
        // Right panel - Features section
        JPanel featuresPanel = createFeaturesPanel();
        
        containerPanel.add(formPanel);
        containerPanel.add(Box.createRigidArea(new Dimension(40, 0))); // Spacing between panels
        containerPanel.add(featuresPanel);
        
        // Footer panel
        JPanel footerPanel = createFooterPanel();
        
        // Add panels to the content pane
        contentPane.add(containerPanel, BorderLayout.CENTER);
        contentPane.add(footerPanel, BorderLayout.SOUTH);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    // Custom JPanel class that paints the background image
    private static class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;
        
        public BackgroundPanel(String imagePath) {
            // Try to load the background image
            try {
                // Try to load from file path
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    backgroundImage = ImageIO.read(imageFile);
                    System.out.println("Background image loaded successfully from: " + imagePath);
                } else {
                    // Try to load from resources
                    URL resourceURL = FoodOrderingLogin.class.getResource(imagePath);
                    if (resourceURL != null) {
                        backgroundImage = ImageIO.read(resourceURL);
                        System.out.println("Background image loaded successfully from resources");
                    } else {
                        System.out.println("Could not find background image at: " + imagePath);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading background image: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Fill with a solid color first (fallback)
            g.setColor(MAIN_BG_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());
            
            // If we have a background image, paint it across the entire panel
            if (backgroundImage != null) {
                Graphics2D g2d = (Graphics2D) g;
                
                // Set high-quality rendering hints
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, 
                                    RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw the image to fill the entire panel
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                
                // Add semi-transparent overlay to make content readable
                g2d.setColor(new Color(150, 150, 150, 100));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }
    
    private static JPanel createFormPanel(JFrame parentFrame) {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        formPanel.setMaximumSize(new Dimension(FORM_WIDTH, Integer.MAX_VALUE));
        formPanel.setPreferredSize(new Dimension(FORM_WIDTH, 500));
        
        // Add drop shadow
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            new ShadowBorder(), 
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        // Logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setBackground(Color.WHITE);
        
        try {
            // Try to load the logo image from the defined path
            ImageIcon logoIcon = new ImageIcon(LOGO_IMAGE_PATH);
            // Resize image if needed
            Image img = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(img);
            JLabel logoLabel = new JLabel(logoIcon);
            logoPanel.add(logoLabel);
            System.out.println("Logo loaded successfully from: " + LOGO_IMAGE_PATH);
        } catch (Exception e) {
            // Fallback to text logo
            JLabel logoLabel = new JLabel("Italian Bites");
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
            logoLabel.setForeground(BUTTON_COLOR);
            logoPanel.add(logoLabel);
            System.out.println("Could not load logo image: " + e.getMessage());
        }
        
        // Welcome back text - Modified to be right-aligned
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setMaximumSize(new Dimension(FORM_WIDTH - 40, 30));
        
        JLabel welcomeLabel = new JLabel("Welcome back");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(70, 70, 70));
        welcomePanel.add(welcomeLabel);
        
        // Add some vertical spacing after the logo
        formPanel.add(logoPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(welcomePanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Username field
        JTextField usernameField = createStyledTextField("Enter Username");
        formPanel.add(usernameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(FORM_WIDTH - 40, 40));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        passwordField.setText("Password"); // This is just for display, in a real app use a placeholder listener
        
        // Add placeholder behavior for password field
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Password");
                }
            }
        });
        
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Remember me checkbox
        JPanel rememberPanel = new JPanel();
        rememberPanel.setLayout(new BoxLayout(rememberPanel, BoxLayout.X_AXIS));
        rememberPanel.setBackground(Color.WHITE);
        rememberPanel.setMaximumSize(new Dimension(FORM_WIDTH - 40, 30));
        rememberPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JCheckBox rememberCheck = new JCheckBox("Remember me");
        rememberCheck.setBackground(Color.WHITE);
        rememberCheck.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Forgot password link
        JLabel forgotLabel = new JLabel("Forgot password?");
        forgotLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotLabel.setForeground(BUTTON_COLOR);
        forgotLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Make it look clickable
        forgotLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                forgotLabel.setText("<html><u>Forgot password?</u></html>");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                forgotLabel.setText("Forgot password?");
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                showForgotPasswordDialog(parentFrame);
            }
        });
        
        rememberPanel.add(rememberCheck);
        rememberPanel.add(Box.createHorizontalGlue());
        rememberPanel.add(forgotLabel);
        
        formPanel.add(rememberPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Login button - Fix: Make sure it's properly styled and visible
        JButton loginButton = new JButton("LOGIN");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(BUTTON_COLOR);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(FORM_WIDTH - 40, 40));
        loginButton.setMaximumSize(new Dimension(FORM_WIDTH - 40, 40));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                // Basic validation
                if (username.isEmpty() || username.equals("Enter Username")) {
                    JOptionPane.showMessageDialog(parentFrame, 
                        "Please enter your username", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (password.isEmpty() || password.equals("Password")) {
                    JOptionPane.showMessageDialog(parentFrame, 
                        "Please enter your password", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // In a real application, you would validate credentials here
                // For demo purposes, we'll just show a success message
                if (username.equals("admin") && password.equals("admin123")) {
                    JOptionPane.showMessageDialog(parentFrame, 
                        "Login successful! Welcome, " + username + "!", 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // In a real app, you would open the main application window here
                    // For now, we'll just show a demo message
                    showMainApplicationWindow(parentFrame, username);
                } else {
                    JOptionPane.showMessageDialog(parentFrame, 
                        "Invalid username or password", 
                        "Login Failed", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Create a panel for the button to ensure proper alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setMaximumSize(new Dimension(FORM_WIDTH - 40, 40));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(loginButton);
        
        formPanel.add(buttonPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Need to create an account?
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        signupPanel.setBackground(Color.WHITE);
        signupPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        signupPanel.setMaximumSize(new Dimension(FORM_WIDTH - 40, 30));
        
        JLabel needAccountLabel = new JLabel("Need an account? ");
        needAccountLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        
        // Fix: Make "Sign up" always visible (not tied to "Forgot password" hover)
        JLabel signupLabel = new JLabel("Sign up");
        signupLabel.setFont(new Font("Arial", Font.BOLD, 11));
        signupLabel.setForeground(BUTTON_COLOR);
        signupLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add clickable functionality to the signup label
        signupLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showSignupDialog(parentFrame);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                signupLabel.setText("<html><u>Sign up</u></html>");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                signupLabel.setText("Sign up");
            }
        });
        
        signupPanel.add(needAccountLabel);
        signupPanel.add(signupLabel);
        
        formPanel.add(signupPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Social media login buttons
        JPanel socialPanel = new JPanel();
        socialPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        socialPanel.setBackground(Color.WHITE);
        
        JLabel orLabel = new JLabel("OR LOGIN WITH");
        orLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        orLabel.setForeground(Color.GRAY);
        
        JPanel socialButtonPanel = new JPanel();
        socialButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        socialButtonPanel.setBackground(Color.WHITE);
        
        // Modified social buttons to be larger and more visible
        JButton fbButton = createSocialButton("FB", parentFrame);
        JButton googleButton = createSocialButton("G+", parentFrame);
        JButton twitterButton = createSocialButton("TW", parentFrame);
        
        socialButtonPanel.add(fbButton);
        socialButtonPanel.add(googleButton);
        socialButtonPanel.add(twitterButton);
        
        socialPanel.add(orLabel);
        formPanel.add(socialPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(socialButtonPanel);
        
        return formPanel;
    }
    
    private static void showForgotPasswordDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Reset Password", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(parent);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Reset Your Password");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel instructionLabel = new JLabel("Enter your email to receive a password reset link");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextField emailField = new JTextField();
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton resetButton = new JButton("Send Reset Link");
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(BUTTON_COLOR);
        resetButton.setFocusPainted(false);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.setMaximumSize(new Dimension(200, 40));
        resetButton.addActionListener(e -> {
            String email = emailField.getText();
            if (email.isEmpty() || !email.contains("@")) {
                JOptionPane.showMessageDialog(dialog, 
                    "Please enter a valid email address", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dialog, 
                    "Password reset link has been sent to " + email, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            }
        });
        
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(instructionLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(emailField);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(resetButton);
        
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }
    
    private static void showSignupDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Create Account", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(parent);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Create Your Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Form fields
        JTextField nameField = createFormField("Full Name");
        JTextField emailField = createFormField("Email Address");
        JPasswordField passwordField = createPasswordField("Password");
        JPasswordField confirmPasswordField = createPasswordField("Confirm Password");
        
        JButton signupButton = new JButton("SIGN UP");
        signupButton.setForeground(Color.WHITE);
        signupButton.setBackground(BUTTON_COLOR);
        signupButton.setFocusPainted(false);
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.setMaximumSize(new Dimension(200, 40));
        signupButton.addActionListener(e -> {
            // Validate form
            if (nameField.getText().isEmpty() || nameField.getText().equals("Full Name")) {
                showFieldError(dialog, "Please enter your full name");
                return;
            }
            
            String email = emailField.getText();
            if (email.isEmpty() || email.equals("Email Address") || !email.contains("@")) {
                showFieldError(dialog, "Please enter a valid email address");
                return;
            }
            
            String password = new String(passwordField.getPassword());
            if (password.isEmpty() || password.equals("Password")) {
                showFieldError(dialog, "Please enter a password");
                return;
            }
            
            String confirmPassword = new String(confirmPasswordField.getPassword());
            if (!password.equals(confirmPassword)) {
                showFieldError(dialog, "Passwords do not match");
                return;
            }
            
            // In a real app, you would create the account here
            JOptionPane.showMessageDialog(dialog, 
                "Account created successfully!\nWelcome, " + nameField.getText() + "!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });
        
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(nameField);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(emailField);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(passwordField);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(confirmPasswordField);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        contentPanel.add(signupButton);
        
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }
    
    private static JTextField createFormField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
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
    
    private static JPasswordField createPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField(placeholder);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        field.setEchoChar((char)0); // Show plain text for placeholder
        
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setEchoChar('•');
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setEchoChar((char)0);
                    field.setText(placeholder);
                }
            }
        });
        
        return field;
    }
    
    private static void showFieldError(JDialog parent, String message) {
        JOptionPane.showMessageDialog(parent, 
            message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    
    private static void showMainApplicationWindow(JFrame parent, String username) {
        JFrame appFrame = new JFrame("Food Ordering App - Welcome " + username);
        appFrame.setSize(800, 600);
        appFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        appFrame.setLocationRelativeTo(parent);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel welcomeLabel = new JLabel("Welcome to Italian Bites, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel infoLabel = new JLabel("<html><div style='text-align: center;'>"
            + "This would be the main application window in a full implementation.<br>"
            + "You would see the menu, order options, and other features here.</div></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(infoLabel, BorderLayout.CENTER);
        
        appFrame.add(mainPanel);
        appFrame.setVisible(true);
    }
    
    private static JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setPreferredSize(new Dimension(FORM_WIDTH - 40, 40));
        field.setMaximumSize(new Dimension(FORM_WIDTH - 40, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        field.setForeground(Color.GRAY);
        
        // Add focus listener for placeholder behavior
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
    
    // Modified social button to be larger and text more visible
    private static JButton createSocialButton(String text, JFrame parent) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(40, 40)); // Increased size
        button.setFont(new Font("Arial", Font.BOLD, 12)); // Make text more visible
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add action listener
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String provider = "";
                switch(text) {
                    case "FB":
                        provider = "Facebook";
                        break;
                    case "G+":
                        provider = "Google";
                        break;
                    case "TW":
                        provider = "Twitter";
                        break;
                }
                
                JOptionPane.showMessageDialog(parent, 
                    "This would normally redirect to " + provider + " authentication.\n" +
                    "In a full implementation, you would be able to login with your " + 
                    provider + " account.", 
                    provider + " Login", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        return button;
    }
    
    private static JPanel createFeaturesPanel() {
        JPanel featuresPanel = new JPanel();
        featuresPanel.setLayout(new BoxLayout(featuresPanel, BoxLayout.Y_AXIS));
        featuresPanel.setOpaque(false); // Make transparent to show background
        featuresPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Add a white semi-transparent background to improve readability
        JPanel featuresContent = new JPanel();
        featuresContent.setLayout(new BoxLayout(featuresContent, BoxLayout.Y_AXIS));
        featuresContent.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent white
        featuresContent.setBorder(new EmptyBorder(15, 15, 15, 15));
        featuresContent.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Title
        JLabel titleLabel = new JLabel("Login to Your Account");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        featuresContent.add(titleLabel);
        featuresContent.add(Box.createRigidArea(new Dimension(0, 5)));
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Access your favorite meals");
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        featuresContent.add(subtitleLabel);
        featuresContent.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Feature items - Modified for food ordering system login
        String[] features = {
            "Access your saved favorite restaurants",
            "View your order history",
            "Track ongoing deliveries",
            "Use saved payment methods for faster checkout",
            "Redeem rewards and exclusive offers"
        };
        
        for (String feature : features) {
            JPanel featureItem = new JPanel();
            featureItem.setLayout(new BoxLayout(featureItem, BoxLayout.X_AXIS));
            featureItem.setAlignmentX(Component.LEFT_ALIGNMENT);
            featureItem.setOpaque(false);
            
            JLabel checkmark = new JLabel("✓");
            checkmark.setForeground(Color.GRAY);
            featureItem.add(checkmark);
            featureItem.add(Box.createRigidArea(new Dimension(10, 0)));
            
            JLabel featureText = new JLabel(feature);
            featureText.setFont(NORMAL_FONT);
            featureItem.add(featureText);
            
            featuresContent.add(featureItem);
            featuresContent.add(Box.createRigidArea(new Dimension(0, 15)));
        }
        
        featuresPanel.add(featuresContent);
        
        return featuresPanel;
    }
    
    private static JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(15, 60, 15, 60)
        ));
        
        // Left section - Menu links
        JPanel linksPanel = new JPanel();
        linksPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        linksPanel.setBackground(Color.WHITE);
        
        String[] links = {"Explore", "Menu"};
        for (String link : links) {
            JLabel linkLabel = new JLabel(link);
            linkLabel.setForeground(new Color(100, 100, 100));
            linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            linkLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    linkLabel.setText("<html><u>" + link + "</u></html>");
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    linkLabel.setText(link);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null, link + " page will be implemented here.");
                }
            });
            linksPanel.add(linkLabel);
        }
        
        // Right section - Copyright and social icons
        JPanel rightFooter = new JPanel();
        rightFooter.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightFooter.setBackground(Color.WHITE);
        
        JLabel copyrightLabel = new JLabel("© 2025 Company. All rights and copy rights reserved.");
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        copyrightLabel.setForeground(Color.GRAY);
        
        JPanel socialIcons = new JPanel();
        socialIcons.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        socialIcons.setBackground(Color.WHITE);
        
        JLabel fb = new JLabel("□");
        JLabel twitter = new JLabel("□");
        JLabel instagram = new JLabel("□");
        
        socialIcons.add(fb);
        socialIcons.add(twitter);
        socialIcons.add(instagram);
        
        rightFooter.add(copyrightLabel);
        rightFooter.add(socialIcons);
        
        footerPanel.add(linksPanel, BorderLayout.WEST);
        footerPanel.add(rightFooter, BorderLayout.EAST);
        
        return footerPanel;
    }
    
    // Custom border class to create shadow effect
    private static class ShadowBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0, 0, 0, 20));
            
            for(int i = 0; i < 5; i++) {
                g2d.drawRect(x + i, y + i, width - i * 2 - 1, height - i * 2 - 1);
            }
        }
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 5, 5, 5);
        }
    }
}