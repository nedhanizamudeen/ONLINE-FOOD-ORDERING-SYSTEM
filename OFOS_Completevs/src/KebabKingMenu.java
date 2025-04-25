import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;

public class KebabKingMenu {
    private static final Color MAIN_BG_COLOR = new Color(153, 0, 0); // Deep red background
    private static final Color ACCENT_COLOR = new Color(60, 60, 60);   // Dark gray for text
    private static final Color BUTTON_COLOR = new Color(0, 153, 51);  // Green accent color
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 28);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Font FOOD_TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 14);
    
    // Background image path - Replace with the actual path to your downloaded image
    private static final String BG_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\top-view-box-with-salad-copy-space (2).jpg";
    
    // Food images - Replace with actual paths
    private static final String SEEKH_ROLL_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\Seekh-Kabab-Roll-02.jpg";
    private static final String CHILLI_ROLL_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\chilly-chicken-roll_.jpg";
    private static final String SEEKH_KEBAB_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\seekhkebab.jpg";
    private static final String SHAMI_KEBAB_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\Chicken-Shami-Kabab-03.jpg";
    private static final String HARA_BHARA_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\Hara-Bhara-Kebabs-750x750.jpg";
    
    // Menu items data
    private static final String[] FOOD_NAMES = {
        "Chicken Seekh Kebab Roll",
        "Chicken Chilli Roll",
        "Chicken Seekh Kebab",
        "Chicken Shami Kebab",
        "Chicken Hara Bhara Kebab"
    };
    
    private static final String[] FOOD_INGREDIENTS = {
        "Juicy seekh kebabs wrapped in soft, flaky parathas, layered with crisp onions, fresh veggies and a hint of tangy chutney--perfectly rolled for a bold flavorful bite.",
        "Spicy chilli chicken wrapped in a flaky paratha with green peppers, onions, and our signature sauce.",
        "Juicy, chargrilled chicken seekh kebabs infused with aromatic herbs and spices, served with crisp lettuce and a tangy, smoky dipping sauce.",
        "Tender chicken shami kebabs, crisped to golden perfection, bursting with warm spices and herbs, served with sharp onion rings, zesty lemon, and a refreshing mint chutney.",
        "Vibrant chicken hara bhara kebabs, packed with fresh greens and subtle spices, seared to perfection and served with crisp salad and a cooling mint-coriander chutney."
    };
    
    private static final double[] FOOD_PRICES = {180, 160, 220, 200, 150};
    
    // Cart functionality
    private static Map<String, Integer> cart = new HashMap<>();
    private static JButton cartButton;
    private static JFrame mainFrame;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    
    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mainFrame = new JFrame("Kebab King - Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        
        // Create main panel with background image
        BackgroundPanel mainPanel = new BackgroundPanel(BG_IMAGE_PATH);
        mainPanel.setLayout(new BorderLayout());
        
        // Create header panel
        JPanel headerPanel = createHeaderPanel();
        
        // Create menu panel
        JPanel menuPanel = createMenuPanel();
        
        // Create footer panel
        JPanel footerPanel = createFooterPanel();
        
        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Position the menu panel in the red part of the background
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        
        // Add some padding to the left to position menu on the right side
        contentPanel.add(Box.createHorizontalStrut(450), BorderLayout.WEST);
        
        // Add menu panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        mainFrame.add(mainPanel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
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
    
    private static JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        
        // Restaurant title and back button
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        
        JButton backButton = new JButton("< Back");
        backButton.setFont(NORMAL_FONT);
        backButton.setForeground(ACCENT_COLOR);
        backButton.setBackground(new Color(255, 255, 255, 200));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Back button functionality
        backButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame, "Returning to previous screen...");
            // In a real app, you would navigate back to the previous screen
        });
        
        JLabel titleLabel = new JLabel("Kebab King");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(ACCENT_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Grilled Perfection");
        subtitleLabel.setFont(TITLE_FONT);
        subtitleLabel.setForeground(ACCENT_COLOR);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        titlePanel.add(backButton);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        titlePanel.add(subtitleLabel);
        
        // Right side - User profile and cart
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setOpaque(false);
        
        cartButton = new JButton("Cart (0)");
        styleButton(cartButton);
        
        // Cart button functionality
        cartButton.addActionListener(e -> showCartDialog());
        
        JButton profileButton = new JButton("My Account");
        styleButton(profileButton);
        
        // Profile button functionality
        profileButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame, 
                "Account management would go here", 
                "My Account", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        userPanel.add(profileButton);
        userPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        userPanel.add(cartButton);
        
        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(userPanel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private static JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setOpaque(false);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // Category header
        JLabel categoryLabel = new JLabel("Signature Kebabs & Rolls");
        categoryLabel.setFont(TITLE_FONT);
        categoryLabel.setForeground(ACCENT_COLOR);
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuPanel.add(categoryLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Menu items grid
        JPanel foodGrid = new JPanel();
        foodGrid.setLayout(new BoxLayout(foodGrid, BoxLayout.Y_AXIS));
        foodGrid.setOpaque(false);
        foodGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Create menu item panels
        JPanel seekhRollPanel = createFoodItemPanel(
            FOOD_NAMES[0],
            FOOD_INGREDIENTS[0],
            FOOD_PRICES[0],
            SEEKH_ROLL_IMAGE_PATH
        );
        
        JPanel chilliRollPanel = createFoodItemPanel(
            FOOD_NAMES[1],
            FOOD_INGREDIENTS[1],
            FOOD_PRICES[1],
            CHILLI_ROLL_IMAGE_PATH
        );
        
        JPanel seekhKebabPanel = createFoodItemPanel(
            FOOD_NAMES[2],
            FOOD_INGREDIENTS[2],
            FOOD_PRICES[2],
            SEEKH_KEBAB_IMAGE_PATH
        );
        
        JPanel shamiKebabPanel = createFoodItemPanel(
            FOOD_NAMES[3],
            FOOD_INGREDIENTS[3],
            FOOD_PRICES[3],
            SHAMI_KEBAB_IMAGE_PATH
        );
        
        JPanel haraBharaPanel = createFoodItemPanel(
            FOOD_NAMES[4],
            FOOD_INGREDIENTS[4],
            FOOD_PRICES[4],
            HARA_BHARA_IMAGE_PATH
        );
        
        foodGrid.add(seekhRollPanel);
        foodGrid.add(Box.createRigidArea(new Dimension(0, 15)));
        foodGrid.add(chilliRollPanel);
        foodGrid.add(Box.createRigidArea(new Dimension(0, 15)));
        foodGrid.add(seekhKebabPanel);
        foodGrid.add(Box.createRigidArea(new Dimension(0, 15)));
        foodGrid.add(shamiKebabPanel);
        foodGrid.add(Box.createRigidArea(new Dimension(0, 15)));
        foodGrid.add(haraBharaPanel);
        
        menuPanel.add(foodGrid);
        
        return menuPanel;
    }
    
    private static JPanel createFoodItemPanel(String name, String ingredients, double price, String imagePath) {
        // More transparent panel - with increased padding
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(255, 255, 255, 180)); // More transparent background
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        panel.setLayout(new BorderLayout(20, 0)); // Increased spacing between image and text
        panel.setOpaque(false); // Make it non-opaque to allow custom painting
        panel.setBorder(BorderFactory.createCompoundBorder(
            new ShadowBorder(),
            BorderFactory.createEmptyBorder(20, 20, 20, 20) // Increased padding from 12 to 20
        ));
        
        // Food image panel - 250x250 size
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(250, 250));
        imagePanel.setBackground(new Color(240, 240, 240, 150)); // More transparent image background
        
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage();
            Image resizedImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            System.out.println("Could not load image: " + e.getMessage());
            JLabel placeholderImage = new JLabel("Food Image");
            placeholderImage.setHorizontalAlignment(JLabel.CENTER);
            placeholderImage.setFont(new Font("Arial", Font.BOLD, 16));
            placeholderImage.setForeground(Color.DARK_GRAY);
            imagePanel.add(placeholderImage);
        }
        
        // Food info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea ingredientsArea = new JTextArea(ingredients);
        ingredientsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        ingredientsArea.setLineWrap(true);
        ingredientsArea.setWrapStyleWord(true);
        ingredientsArea.setEditable(false);
        ingredientsArea.setOpaque(false);
        ingredientsArea.setForeground(ACCENT_COLOR);
        ingredientsArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel priceLabel = new JLabel("₹" + String.format("%.2f", price));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(ACCENT_COLOR);
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Add components to info panel with more spacing
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(ingredientsArea);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        infoPanel.add(priceLabel);
        
        // Button panel at the bottom right
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        
        JButton addButton = new JButton("Add to Cart");
        styleButton(addButton);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add to cart button functionality
        addButton.addActionListener(e -> {
            addToCart(name, price);
            JOptionPane.showMessageDialog(panel, 
                "Added " + name + " to cart!", 
                "Item Added", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        buttonPanel.add(addButton);
        
        // Right side panel to hold info and button
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(infoPanel, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add panels to main food panel
        panel.add(imagePanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);
        
        // Make the panel a bit interactive
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.repaint();
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                panel.repaint();
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
            public void mousePressed(MouseEvent e) {
                panel.setBackground(new Color(245, 245, 245, 200));
                panel.repaint();
            }
            
            public void mouseReleased(MouseEvent e) {
                panel.setBackground(new Color(255, 255, 255, 180));
                panel.repaint();
            }
        });
        
        return panel;
    }
    
    private static JPanel createFooterPanel() {
        // More transparent footer panel
        JPanel footerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(255, 255, 255, 180));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setOpaque(false);
        footerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200, 180)),
            BorderFactory.createEmptyBorder(12, 30, 12, 30)
        ));
        
        // Left section - Restaurant info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setOpaque(false);
        
        JLabel infoLabel = new JLabel("Kebab King • ★ 4.8 • 25-40 min delivery");
        infoLabel.setFont(NORMAL_FONT);
        infoLabel.setForeground(Color.GRAY);
        
        infoPanel.add(infoLabel);
        
        // Right section - Copyright
        JPanel rightFooter = new JPanel();
        rightFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightFooter.setOpaque(false);
        
        JLabel copyrightLabel = new JLabel("© 2025 Kebab King. All rights reserved.");
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        copyrightLabel.setForeground(Color.GRAY);
        
        rightFooter.add(copyrightLabel);
        
        footerPanel.add(infoPanel, BorderLayout.WEST);
        footerPanel.add(rightFooter, BorderLayout.EAST);
        
        return footerPanel;
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
            g2d.setColor(new Color(0, 0, 0, 15)); // More transparent shadow
            
            for(int i = 0; i < 3; i++) {
                g2d.drawRect(x + i, y + i, width - i * 2 - 1, height - i * 2 - 1);
            }
        }
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(3, 3, 3, 3);
        }
    }
    
    // Cart functionality methods
    private static void addToCart(String itemName, double price) {
        cart.put(itemName, cart.getOrDefault(itemName, 0) + 1);
        updateCartButton();
    }
    
    private static void updateCartButton() {
        int totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();
        cartButton.setText("Cart (" + totalItems + ")");
    }
    
    private static void showCartDialog() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, 
                "Your cart is empty!", 
                "Cart", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Create a dialog to show cart contents
        JDialog cartDialog = new JDialog(mainFrame, "Your Cart", true);
        cartDialog.setSize(500, 400);
        cartDialog.setLayout(new BorderLayout());
        
        // Create panel for cart items
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add cart items
        double total = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double price = getPriceForItem(itemName);
            double itemTotal = price * quantity;
            total += itemTotal;
            
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            
            JLabel nameLabel = new JLabel(itemName + " x" + quantity);
            JLabel priceLabel = new JLabel("₹" + String.format("%.2f", itemTotal));
            
            // Add buttons to adjust quantity
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton minusButton = new JButton("-");
            JButton plusButton = new JButton("+");
            
            styleSmallButton(minusButton);
            styleSmallButton(plusButton);
            
            minusButton.addActionListener(e -> {
                adjustCartQuantity(itemName, -1);
                cartDialog.dispose();
                showCartDialog();
            });
            
            plusButton.addActionListener(e -> {
                adjustCartQuantity(itemName, 1);
                cartDialog.dispose();
                showCartDialog();
            });
            
            buttonPanel.add(minusButton);
            buttonPanel.add(plusButton);
            
            itemPanel.add(nameLabel, BorderLayout.WEST);
            itemPanel.add(priceLabel, BorderLayout.CENTER);
            itemPanel.add(buttonPanel, BorderLayout.EAST);
            
            itemsPanel.add(itemPanel);
            itemsPanel.add(new JSeparator());
        }
        
        // Add scroll pane for many items
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        cartDialog.add(scrollPane, BorderLayout.CENTER);
        
        // Add total panel at bottom
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel totalLabel = new JLabel("Total: ₹" + String.format("%.2f", total));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JButton checkoutButton = new JButton("Proceed to Checkout");
        styleButton(checkoutButton);
        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame, "Proceeding to checkout...");
            mainFrame.dispose(); // optional: closes the current window
            Payment.main(new String[]{});
            // In a real application, you would navigate to the checkout screen
        });
        
        totalPanel.add(totalLabel, BorderLayout.WEST);
        totalPanel.add(checkoutButton, BorderLayout.EAST);
        
        cartDialog.add(totalPanel, BorderLayout.SOUTH);
        cartDialog.setLocationRelativeTo(mainFrame);
        cartDialog.setVisible(true);
    }
    
    private static void adjustCartQuantity(String itemName, int change) {
        int newQuantity = cart.getOrDefault(itemName, 0) + change;
        if (newQuantity <= 0) {
            cart.remove(itemName);
        } else {
            cart.put(itemName, newQuantity);
        }
        updateCartButton();
    }
    
    private static double getPriceForItem(String itemName) {
        for (int i = 0; i < FOOD_NAMES.length; i++) {
            if (FOOD_NAMES[i].equals(itemName)) {
                return FOOD_PRICES[i];
            }
        }
        return 0;
    }
    
    private static void styleSmallButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(BUTTON_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(30, 20));
    }
}