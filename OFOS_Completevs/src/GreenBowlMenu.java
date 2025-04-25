import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;

public class GreenBowlMenu {
    private static final Color MAIN_BG_COLOR = new Color(76, 175, 80); // Green background
    private static final Color ACCENT_COLOR = new Color(60, 60, 60);   // Dark gray for text
    private static final Color BUTTON_COLOR = new Color(0, 150, 136);  // Teal accent color
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 28);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Font FOOD_TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 14);
    
    // Background image path - Replace with the actual path to your downloaded image
    private static final String BG_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\top-view-box-with-salad-copy-space (2).jpg";
    
    // Food images - Replace with actual paths
    private static final String MUSHROOM_SALAD_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\fried-mushrooms-onions1.jpg";
    private static final String CHICKEN_SALAD_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\StockCake-Delicious chicken salad_1745365730.jpg";
    private static final String MANGO_SMOOTHIE_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\StockCake-Mango Smoothie Delight_1745366051.jpg";
    private static final String STRAWBERRY_SMOOTHIE_IMAGE_PATH = "C:\\Users\\pragu\\Downloads\\javapictures\\strawberrysmoothie.jpg";
    
    // Menu items data
    private static final String[] FOOD_NAMES = {
        "Mushroom Salad",
        "Chicken Salad",
        "Mango Smoothie",
        "Strawberry Smoothie"
    };
    
    private static final String[] FOOD_INGREDIENTS = {
        "Sautéed mushrooms and onions tossed in rich butter and herbs, served warm with a hint of garlic and fresh chives.",
        "Grilled chicken served over a fresh medley of cucumbers, olives, tomatoes, and herbs -- a vibrant and hearty salad bursting with Mediterranean flavor.",
        "Sweet mangoes blended to creamy perfection with honey, and a hint of cardamom for a refreshing drink.",
        "Creamy strawberry smoothie made with fresh strawberries and a touch of sweetness, blended smooth and served chilled with a fruity finish."
    };
    
    private static final double[] FOOD_PRICES = {220, 280, 180, 190};
    
    // Cart functionality
    private static Map<String, Integer> cartItems = new HashMap<>();
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
        
        mainFrame = new JFrame("Green Bowl - Menu");
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
        
        // Position the menu panel in the green part of the background
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
        
        // Add action listener for back button
        backButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame, "Going back to previous screen...");
            // In a real application, you would navigate back to the previous screen
        });
        
        JLabel titleLabel = new JLabel("Green Bowl");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(ACCENT_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Menu");
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
        
        // Add action listener for cart button
        cartButton.addActionListener(e -> showCartDialog());
        
        JButton profileButton = new JButton("My Account");
        styleButton(profileButton);
        
        // Add action listener for profile button
        profileButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame, "Account information would be displayed here");
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
        
        // Category headers
        JLabel saladCategoryLabel = new JLabel("Fresh Salads");
        saladCategoryLabel.setFont(TITLE_FONT);
        saladCategoryLabel.setForeground(ACCENT_COLOR);
        saladCategoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuPanel.add(saladCategoryLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Salads grid
        JPanel saladGrid = new JPanel();
        saladGrid.setLayout(new BoxLayout(saladGrid, BoxLayout.Y_AXIS));
        saladGrid.setOpaque(false);
        saladGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Create salad item panels
        JPanel mushroomSaladPanel = createFoodItemPanel(
            FOOD_NAMES[0],
            FOOD_INGREDIENTS[0],
            FOOD_PRICES[0],
            MUSHROOM_SALAD_IMAGE_PATH
        );
        
        JPanel chickenSaladPanel = createFoodItemPanel(
            FOOD_NAMES[1],
            FOOD_INGREDIENTS[1],
            FOOD_PRICES[1],
            CHICKEN_SALAD_IMAGE_PATH
        );
        
        saladGrid.add(mushroomSaladPanel);
        saladGrid.add(Box.createRigidArea(new Dimension(0, 15)));
        saladGrid.add(chickenSaladPanel);
        
        menuPanel.add(saladGrid);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Smoothies category
        JLabel smoothieCategoryLabel = new JLabel("Refreshing Smoothies");
        smoothieCategoryLabel.setFont(TITLE_FONT);
        smoothieCategoryLabel.setForeground(ACCENT_COLOR);
        smoothieCategoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuPanel.add(smoothieCategoryLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Smoothies grid
        JPanel smoothieGrid = new JPanel();
        smoothieGrid.setLayout(new BoxLayout(smoothieGrid, BoxLayout.Y_AXIS));
        smoothieGrid.setOpaque(false);
        smoothieGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Create smoothie item panels
        JPanel mangoSmoothiePanel = createFoodItemPanel(
            FOOD_NAMES[2],
            FOOD_INGREDIENTS[2],
            FOOD_PRICES[2],
            MANGO_SMOOTHIE_IMAGE_PATH
        );
        
        JPanel strawberrySmoothiePanel = createFoodItemPanel(
            FOOD_NAMES[3],
            FOOD_INGREDIENTS[3],
            FOOD_PRICES[3],
            STRAWBERRY_SMOOTHIE_IMAGE_PATH
        );
        
        smoothieGrid.add(mangoSmoothiePanel);
        smoothieGrid.add(Box.createRigidArea(new Dimension(0, 15)));
        smoothieGrid.add(strawberrySmoothiePanel);
        
        menuPanel.add(smoothieGrid);
        
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
        
        // Food image panel
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
        
        // Food name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(ACCENT_COLOR);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Ingredients description
        JTextArea ingredientsArea = new JTextArea(ingredients);
        ingredientsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        ingredientsArea.setLineWrap(true);
        ingredientsArea.setWrapStyleWord(true);
        ingredientsArea.setEditable(false);
        ingredientsArea.setOpaque(false);
        ingredientsArea.setForeground(ACCENT_COLOR);
        ingredientsArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Price
        JLabel priceLabel = new JLabel("₹" + String.format("%.2f", price));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(ACCENT_COLOR);
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Add components to info panel with spacing
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(ingredientsArea);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        infoPanel.add(priceLabel);
        
        // Button panel at the bottom right
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        
        // "Add to Cart" button
        JButton addButton = new JButton("Add to Cart");
        styleButton(addButton);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add action listener for add to cart button
        addButton.addActionListener(e -> {
            addToCart(name, price);
            JOptionPane.showMessageDialog(panel, name + " added to cart!");
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
        
        // Make the panel interactive
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.repaint(); // Trigger repaint with hover effect
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                panel.repaint(); // Reset to normal appearance
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
                g2.setColor(new Color(255, 255, 255, 180)); // More transparent background
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setOpaque(false); // Make it non-opaque to allow custom painting
        footerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200, 180)), // More transparent border
            BorderFactory.createEmptyBorder(12, 30, 12, 30)
        ));
        
        // Left section - Restaurant info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setOpaque(false);
        
        JLabel infoLabel = new JLabel("Green Bowl • ★ 4.9 • 20-35 min delivery");
        infoLabel.setFont(NORMAL_FONT);
        infoLabel.setForeground(Color.GRAY);
        
        infoPanel.add(infoLabel);
        
        // Right section - Copyright
        JPanel rightFooter = new JPanel();
        rightFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightFooter.setOpaque(false);
        
        JLabel copyrightLabel = new JLabel("© 2025 Food Ordering App. All rights reserved.");
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
        cartItems.put(itemName, cartItems.getOrDefault(itemName, 0) + 1);
        updateCartButton();
    }
    
    private static void updateCartButton() {
        int totalItems = cartItems.values().stream().mapToInt(Integer::intValue).sum();
        cartButton.setText("Cart (" + totalItems + ")");
    }
    
    private static void showCartDialog() {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Your cart is empty!", "Cart", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Create a panel to display cart items
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add title
        JLabel titleLabel = new JLabel("Your Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartPanel.add(titleLabel);
        cartPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Calculate total
        double total = 0;
        
        // Add cart items
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double price = 0;
            
            // Find the price of the item
            for (int i = 0; i < FOOD_NAMES.length; i++) {
                if (FOOD_NAMES[i].equals(itemName)) {
                    price = FOOD_PRICES[i];
                    break;
                }
            }
            
            double itemTotal = price * quantity;
            total += itemTotal;
            
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
            itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            JLabel nameLabel = new JLabel(itemName + " x" + quantity);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            
            JLabel priceLabel = new JLabel("₹" + String.format("%.2f", itemTotal));
            priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            itemPanel.add(nameLabel);
            itemPanel.add(Box.createHorizontalGlue());
            itemPanel.add(priceLabel);
            
            cartPanel.add(itemPanel);
            cartPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        // Add total
        cartPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        JSeparator separator = new JSeparator();
        separator.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartPanel.add(separator);
        cartPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.X_AXIS));
        totalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel totalValueLabel = new JLabel("₹" + String.format("%.2f", total));
        totalValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        totalPanel.add(totalLabel);
        totalPanel.add(Box.createHorizontalGlue());
        totalPanel.add(totalValueLabel);
        
        cartPanel.add(totalPanel);
        cartPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Add checkout button
        JButton checkoutButton = new JButton("Proceed to Checkout");
        styleButton(checkoutButton);
        checkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame, "Proceeding to checkout...");
            mainFrame.dispose(); // optional: closes the current window
            Payment.main(new String[]{});
            // In a real application, you would navigate to the checkout screen
        });
        
        cartPanel.add(checkoutButton);
        
        // Show the cart dialog
        JOptionPane.showOptionDialog(
            mainFrame,
            cartPanel,
            "Your Cart",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            new Object[]{},
            null
        );
    }
}