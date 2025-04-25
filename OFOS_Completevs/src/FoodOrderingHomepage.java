import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class FoodOrderingHomepage {
    private static void openRestaurantMenu(int index) {
        if (index == 0) {
            // Spicy Bites Menu
            SpicyBitesMenu.main(new String[]{});
        } else if (index == 1) {
            // Green Bowl Menu
            GreenBowlMenu.main(new String[]{});
        } else if (index == 2) {
            // Bombay Bistro Menu
            BombayBistroMenu.main(new String[]{});
        } else if (index == 3) {
            // Southern Spice Menu
            SouthernSpiceMenu.main(new String[]{});
        } else if (index == 4) {
            // Kebab King Menu
            KebabKingMenu.main(new String[]{});
        } else {
            JOptionPane.showMessageDialog(null, "Invalid restaurant index.");
        }
    }
    private static final Color MAIN_BG_COLOR = new Color(255, 189, 0); // Yellow background from image
    private static final Color ACCENT_COLOR = new Color(80, 80, 80);   // Dark gray for text
    private static final Color BUTTON_COLOR = new Color(233, 30, 99);  // Pink accent color
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 28);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 14);
    
    // Background image path - Replace with the path to your downloaded image
    private static final String BG_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\beverage-fast-food-with-copy-space.jpg";
    
    // Placeholder images for restaurants - Replace with actual paths
    private static final String[] RESTAURANT_IMAGES = {
        "C:\\Users\\pragu\\Downloads\\javapictures\\biryani.jpg",
        "C:\\Users\\pragu\\Downloads\\javapictures\\pexels-chanwalrus-1059905.jpg", 
        "C:\\Users\\pragu\\Downloads\\javapictures\\vadapav.webp",
        "C:\\Users\\pragu\\Downloads\\javapictures\\dosa.jpg",
        "C:\\Users\\pragu\\Downloads\\javapictures\\wallpaperflare.com_wallpaper.jpg"
    };
    
    // Restaurant names
    private static final String[] RESTAURANT_NAMES = {
        "Spicy Bites",
        "Green Bowl",
        "Bombay Bistro",
        "Southern Spice",
        "Kebab King"
    };
    
    // Restaurant ratings
    private static final double[] RESTAURANT_RATINGS = {4.7, 4.5, 4.8, 4.2, 4.6};
    
    // Image aspect ratio (16:9)
    private static final double IMAGE_ASPECT_RATIO = 16.0 / 9.0;
    
    // Cart items counter
    private static int cartItemsCount = 0;
    private static JButton cartButton;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    
    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame("Food Ordering - Homepage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        
        // Create main panel with a background image
        BackgroundPanel mainPanel = new BackgroundPanel(BG_IMAGE_PATH);
        mainPanel.setLayout(new BorderLayout());
        
        // Create header panel
        JPanel headerPanel = createHeaderPanel(frame);
        
        // Create restaurants panel with transparent background
        JPanel restaurantsPanel = createRestaurantsPanel(frame);
        
        // Create footer panel
        JPanel footerPanel = createFooterPanel();
        
        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Wrap restaurants panel in a scroll pane with transparent viewport
        JScrollPane scrollPane = new JScrollPane(restaurantsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        // Position the restaurants panel in the right yellow area
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        
        // Add some padding to the left to position restaurants on the right side
        contentPanel.add(Box.createHorizontalStrut(450), BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        frame.add(mainPanel);
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
                setBackground(MAIN_BG_COLOR); // Fallback color
            }
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Draw the background image, scaling it to fill the panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    
    private static JPanel createHeaderPanel(JFrame frame) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(false); // Make transparent to show background image
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        
        // Left side - Title and subtitle
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Food Delivery");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(ACCENT_COLOR);
        
        JLabel subtitleLabel = new JLabel("Order from your favorite restaurants");
        subtitleLabel.setFont(NORMAL_FONT);
        subtitleLabel.setForeground(ACCENT_COLOR);
        
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        titlePanel.add(subtitleLabel);
        
        // Right side - User profile and cart
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setOpaque(false);
        
        cartButton = new JButton("Cart (0)");
        styleButton(cartButton);
        cartButton.addActionListener(e -> showCartDialog(frame));
        
        JButton profileButton = new JButton("My Account");
        styleButton(profileButton);
        profileButton.addActionListener(e -> showAccountDialog(frame));
        
        userPanel.add(profileButton);
        userPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        userPanel.add(cartButton);
        
        // Add search panel
        JPanel searchPanel = createSearchPanel(frame);
        
        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(userPanel, BorderLayout.EAST);
        headerPanel.add(searchPanel, BorderLayout.SOUTH);
        
        return headerPanel;
    }
    
    private static JPanel createSearchPanel(JFrame frame) {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setOpaque(false); // Make transparent to show background image
        searchPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        
        JTextField searchField = new JTextField("Search for restaurants or food...");
        searchField.setFont(NORMAL_FONT);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        searchField.setForeground(Color.GRAY);
        
        // Add placeholder text behavior
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search for restaurants or food...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search for restaurants or food...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        
        JButton searchButton = new JButton("Search");
        styleButton(searchButton);
        searchButton.setPreferredSize(new Dimension(100, 36));
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            if (!searchText.isEmpty() && !searchText.equals("Search for restaurants or food...")) {
                JOptionPane.showMessageDialog(frame, 
                    "Searching for: " + searchText, 
                    "Search Results", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        searchPanel.add(searchField);
        searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchPanel.add(searchButton);
        
        return searchPanel;
    }
    
    private static JPanel createRestaurantsPanel(JFrame frame) {
        JPanel restaurantsPanel = new JPanel();
        restaurantsPanel.setLayout(new BoxLayout(restaurantsPanel, BoxLayout.Y_AXIS));
        restaurantsPanel.setOpaque(false); // Make transparent to show background image
        restaurantsPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // Category header
        JLabel categoryLabel = new JLabel("Popular Restaurants");
        categoryLabel.setFont(TITLE_FONT);
        categoryLabel.setForeground(ACCENT_COLOR);
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        restaurantsPanel.add(categoryLabel);
        restaurantsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Restaurant panels grid (use a GridLayout panel)
        JPanel restaurantGrid = new JPanel();
        restaurantGrid.setLayout(new BoxLayout(restaurantGrid, BoxLayout.Y_AXIS));
        restaurantGrid.setOpaque(false);
        restaurantGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Create restaurant panels
        for (int i = 0; i < RESTAURANT_NAMES.length; i++) {
            JPanel restaurantPanel = createRestaurantPanel(
                RESTAURANT_NAMES[i],
                RESTAURANT_RATINGS[i],
                RESTAURANT_IMAGES[i],
                frame
            );
            restaurantGrid.add(restaurantPanel);
            
            // Add spacing between restaurant panels
            if (i < RESTAURANT_NAMES.length - 1) {
                restaurantGrid.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }
        
        restaurantsPanel.add(restaurantGrid);
        
        return restaurantsPanel;
    }
    
    private static JPanel createRestaurantPanel(String name, double rating, String imagePath, JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255, 230)); // Slightly transparent white
        panel.setBorder(BorderFactory.createCompoundBorder(
            new ShadowBorder(),
            BorderFactory.createEmptyBorder(0, 0, 8, 0)
        ));
        
        // Restaurant image panel setup with reduced size
        JPanel imagePanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                int width = 300;  // Reduced width from 400 to 300
                int height = (int)(width / IMAGE_ASPECT_RATIO);  // Calculate height based on 16:9 ratio
                return new Dimension(width, height);
            }
        };
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBackground(Color.LIGHT_GRAY); // Placeholder color
        
        try {
            // Load the image with proper scaling to maintain 16:9 aspect ratio
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage();
            
            // Create a label to hold the image
            JLabel imageLabel = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (image != null) {
                        int panelWidth = getWidth();
                        int panelHeight = getHeight();
                        
                        // Calculate dimensions to maintain aspect ratio
                        double aspectRatio = (double) image.getWidth(this) / image.getHeight(this);
                        int drawWidth, drawHeight;
                        
                        // If image is loaded, use its aspect ratio, otherwise use 16:9
                        if (aspectRatio <= 0) aspectRatio = IMAGE_ASPECT_RATIO;
                        
                        drawWidth = panelWidth;
                        drawHeight = (int)(drawWidth / aspectRatio);
                        
                        // Center the image in the panel
                        int x = 0;
                        int y = (panelHeight - drawHeight) / 2;
                        
                        // Draw the image
                        g.drawImage(image, x, y, drawWidth, drawHeight, this);
                    } else {
                        // Draw placeholder if image cannot be loaded
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(0, 0, getWidth(), getHeight());
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("Arial", Font.BOLD, 14));
                        FontMetrics fm = g.getFontMetrics();
                        String text = "Restaurant Image";
                        int textWidth = fm.stringWidth(text);
                        g.drawString(text, (getWidth() - textWidth) / 2, getHeight() / 2);
                    }
                }
            };
            
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Could not load image: " + e.getMessage());
            JLabel placeholderImage = new JLabel("Restaurant Image");
            placeholderImage.setHorizontalAlignment(JLabel.CENTER);
            placeholderImage.setFont(new Font("Arial", Font.BOLD, 14));
            placeholderImage.setForeground(Color.WHITE);
            imagePanel.add(placeholderImage, BorderLayout.CENTER);
        }
        
        // Restaurant info panel - reduced paddings
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(255, 255, 255, 230)); // Slightly transparent white
        infoPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12)); // Reduced padding
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Reduced font size
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Rating panel
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0)); // Reduced spacing
        ratingPanel.setOpaque(false);
        ratingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel ratingStarLabel = new JLabel("★");
        ratingStarLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Reduced font size
        ratingStarLabel.setForeground(new Color(255, 180, 0)); // Gold color for stars
        
        JLabel ratingValueLabel = new JLabel(String.valueOf(rating));
        ratingValueLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Reduced font size
        
        ratingPanel.add(ratingStarLabel);
        ratingPanel.add(ratingValueLabel);
        
        // Order button - smaller button
        JButton orderButton = new JButton("Order Now");
        orderButton.setFont(new Font("Arial", Font.BOLD, 11)); // Reduced font size
        orderButton.setForeground(Color.WHITE);
        orderButton.setBackground(BUTTON_COLOR);
        orderButton.setFocusPainted(false);
        orderButton.setBorderPainted(false);
        orderButton.setOpaque(true);
        orderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        orderButton.addActionListener(e -> {
            addToCart(name);
            JOptionPane.showMessageDialog(frame, 
                "Added " + name + " to your cart!", 
                "Order Placed", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(orderButton);
        
        // Add components to info panel with reduced spacing
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 3))); // Reduced spacing
        infoPanel.add(ratingPanel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 6))); // Reduced spacing
        infoPanel.add(buttonPanel);
        
        // Add panels to main restaurant panel - changed layout to vertical
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        imagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(imagePanel);
        panel.add(infoPanel);
        
        // Custom handling for maintaining panel size with preferred height
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height));
        
        // Make the whole panel clickable
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showRestaurantDetails(frame, name, rating, imagePath);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                panel.setBackground(new Color(250, 250, 250, 230));
                infoPanel.setBackground(new Color(250, 250, 250, 230));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                panel.setBackground(new Color(255, 255, 255, 230));
                infoPanel.setBackground(new Color(255, 255, 255, 230));
            }
        });
        
        return panel;
    }
    
    private static JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBackground(new Color(255, 255, 255, 230)); // Slightly transparent white
        footerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(12, 30, 12, 30) // Reduced padding
        ));
        
        // Left section - Empty now
        JPanel linksPanel = new JPanel();
        linksPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        linksPanel.setOpaque(false);
        
        // Add some footer links
        JLabel aboutLabel = new JLabel("About Us");
        styleFooterLink(aboutLabel);
        
        JLabel contactLabel = new JLabel("Contact");
        styleFooterLink(contactLabel);
        
        JLabel termsLabel = new JLabel("Terms of Service");
        styleFooterLink(termsLabel);
        
        linksPanel.add(aboutLabel);
        linksPanel.add(contactLabel);
        linksPanel.add(termsLabel);
        
        // Right section - Copyright
        JPanel rightFooter = new JPanel();
        rightFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightFooter.setOpaque(false);
        
        JLabel copyrightLabel = new JLabel("© 2025 Food Ordering App. All rights reserved.");
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        copyrightLabel.setForeground(Color.GRAY);
        
        rightFooter.add(copyrightLabel);
        
        footerPanel.add(linksPanel, BorderLayout.WEST);
        footerPanel.add(rightFooter, BorderLayout.EAST);
        
        return footerPanel;
    }
    
    private static void styleFooterLink(JLabel label) {
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setForeground(Color.GRAY);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, 
                    "You clicked: " + label.getText(), 
                    "Footer Link", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(BUTTON_COLOR);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.GRAY);
            }
        });
    }
    
    private static void styleButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(BUTTON_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    // Custom border class to create shadow effect
    private static class ShadowBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0, 0, 0, 20));
            
            for(int i = 0; i < 3; i++) {
                g2d.drawRect(x + i, y + i, width - i * 2 - 1, height - i * 2 - 1);
            }
        }
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(3, 3, 3, 3);
        }
    }
    
    // Button functionality methods
    private static void addToCart(String restaurantName) {
        cartItemsCount++;
        cartButton.setText("Cart (" + cartItemsCount + ")");
    }
    
    private static void showCartDialog(JFrame parent) {
        if (cartItemsCount == 0) {
            JOptionPane.showMessageDialog(parent, 
                "Your cart is empty!", 
                "Shopping Cart", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        JDialog cartDialog = new JDialog(parent, "Your Shopping Cart", true);
        cartDialog.setSize(400, 300);
        cartDialog.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Items in your cart: " + cartItemsCount, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartDialog.add(titleLabel, BorderLayout.NORTH);
        
        // In a real app, you would list the actual items here
        JTextArea itemsArea = new JTextArea();
        itemsArea.setEditable(false);
        itemsArea.setText("1. Order from Spicy Bites\n2. Order from Bombay Bistro");
        itemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        
        cartDialog.add(new JScrollPane(itemsArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton checkoutButton = new JButton("Proceed to Checkout");
        styleButton(checkoutButton);
        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(cartDialog, 
                "Checkout functionality would go here!", 
                "Checkout", 
                JOptionPane.INFORMATION_MESSAGE);
            cartDialog.dispose();
        });
        
        JButton clearButton = new JButton("Clear Cart");
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.GRAY);
        clearButton.addActionListener(e -> {
            cartItemsCount = 0;
            cartButton.setText("Cart (0)");
            cartDialog.dispose();
            JOptionPane.showMessageDialog(parent, 
                "Cart cleared!", 
                "Shopping Cart", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        buttonPanel.add(checkoutButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(clearButton);
        
        cartDialog.add(buttonPanel, BorderLayout.SOUTH);
        cartDialog.setLocationRelativeTo(parent);
        cartDialog.setVisible(true);
    }
    
    private static void showAccountDialog(JFrame parent) {
        JDialog accountDialog = new JDialog(parent, "My Account", true);
        accountDialog.setSize(350, 250);
        accountDialog.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        formPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField("user123");
        formPanel.add(usernameField);
        
        formPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField("user@example.com");
        formPanel.add(emailField);
        
        formPanel.add(new JLabel("Phone:"));
        JTextField phoneField = new JTextField("+1234567890");
        formPanel.add(phoneField);
        
        formPanel.add(new JLabel("Address:"));
        JTextField addressField = new JTextField("123 Food St, City");
        formPanel.add(addressField);
        
        accountDialog.add(formPanel, BorderLayout.CENTER);
        
        JButton saveButton = new JButton("Save Changes");
        styleButton(saveButton);
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(accountDialog, 
                "Account details saved!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            accountDialog.dispose();
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);
        accountDialog.add(buttonPanel, BorderLayout.SOUTH);
        
        accountDialog.setLocationRelativeTo(parent);
        accountDialog.setVisible(true);
    }
    
    private static void showRestaurantDetails(JFrame parent, String name, double rating, String imagePath) {
        JDialog detailsDialog = new JDialog(parent, name + " - Details", true);
        detailsDialog.setSize(500, 400);
        detailsDialog.setLayout(new BorderLayout());
        
        // Header with image
        JPanel headerPanel = new JPanel(new BorderLayout());
        
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            headerPanel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("Restaurant Image", JLabel.CENTER);
            placeholder.setFont(new Font("Arial", Font.BOLD, 16));
            placeholder.setOpaque(true);
            placeholder.setBackground(Color.LIGHT_GRAY);
            placeholder.setPreferredSize(new Dimension(500, 200));
            headerPanel.add(placeholder, BorderLayout.CENTER);
        }
        
        // Rating label
        JLabel ratingLabel = new JLabel("★ " + rating, JLabel.CENTER);
        ratingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        ratingLabel.setForeground(new Color(255, 180, 0));
        ratingLabel.setOpaque(true);
        ratingLabel.setBackground(new Color(0, 0, 0, 150));
        ratingLabel.setForeground(Color.WHITE);
        ratingLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        headerPanel.add(ratingLabel, BorderLayout.SOUTH);
        detailsDialog.add(headerPanel, BorderLayout.NORTH);
        
        // Details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        detailsPanel.add(nameLabel);
        
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setText("This is a sample description for " + name + ". " +
            "In a real application, this would contain actual information about the restaurant, " +
            "its menu items, delivery options, and other details.");
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        
        detailsPanel.add(new JScrollPane(descriptionArea));
        detailsDialog.add(detailsPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton menuButton = new JButton("View Menu");
        styleButton(menuButton);
        menuButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(detailsDialog, 
                "Menu for " + name + " would be displayed here!", 
                "Menu", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        JButton orderButton = new JButton("Order Now");
        styleButton(orderButton);
        orderButton.addActionListener(e -> {
            addToCart(name);
            JOptionPane.showMessageDialog(detailsDialog, 
                "Added " + name + " to your cart!", 
                "Order Placed", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        buttonPanel.add(menuButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(orderButton);
        
        detailsDialog.add(buttonPanel, BorderLayout.SOUTH);
        detailsDialog.setLocationRelativeTo(parent);
        detailsDialog.setVisible(true);
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
}