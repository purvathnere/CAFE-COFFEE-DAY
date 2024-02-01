
package com.myproject.cafeshop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CafeShop extends JFrame {

    private JLabel backgroundLabel;
    private JPanel contentPanel;
    private Map<String, Double> menu;
    private JTextArea selectedItemsTextArea;
    private JLabel totalPriceLabel;
    private double totalPrice;

    public CafeShop() {
        setTitle("CAFE SHOP");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeMenu();

        backgroundLabel = new JLabel(new ImageIcon("C:\\Users\\HP\\OneDrive\\Documents\\NetBeansProjects\\CafeShop\\bg.jpg"));
        backgroundLabel.setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BorderLayout());

        JLabel headingLabel = new JLabel("CAFE COFFEE DAY");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(headingLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMenuDialog();
            }
        });

        addItemButton.setBackground(Color.ORANGE);

        Dimension buttonSize = new Dimension(200, 60);
        addItemButton.setPreferredSize(buttonSize);
        addItemButton.setMinimumSize(buttonSize);

        totalPriceLabel = new JLabel("Total Price: $0.00");
        totalPriceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        totalPriceLabel.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);

        buttonPanel.add(addItemButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(totalPriceLabel, gbc);

        selectedItemsTextArea = new JTextArea();
        selectedItemsTextArea.setEditable(false);
        selectedItemsTextArea.setOpaque(false);
        selectedItemsTextArea.setFont(new Font("Arial", Font.PLAIN, 16));

        Dimension selectedItemsSize = new Dimension(300, 500);
        selectedItemsTextArea.setPreferredSize(selectedItemsSize);
        selectedItemsTextArea.setMinimumSize(selectedItemsSize);

        contentPanel.add(buttonPanel, BorderLayout.CENTER);
        contentPanel.add(new JScrollPane(selectedItemsTextArea), BorderLayout.EAST);

        backgroundLabel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(backgroundLabel);
    }

    private void initializeMenu() {
        menu = new HashMap<>();
        menu.put("Coffee", 5.50);
        menu.put("Tea", 6.80);
        menu.put("Sandwich", 50.00);
        menu.put("Latte", 20.50);
        menu.put("Cappuccino", 60.00);
        menu.put("Espresso", 2.50);
        menu.put("Mocha", 6.20);
        menu.put("Americano", 90.00);
        menu.put("Black Tea", 4.80);
        menu.put("Iced Tea", 7.00);
        menu.put("Hot Chocolate", 30.00);
        menu.put("Croissant", 80.50);
        menu.put("Bagel", 30.80);
        menu.put("Muffin", 50.00);
        menu.put("Sandwich", 80.00);
        menu.put("Salad", 20.50);
        menu.put("Burger", 70.50);
        menu.put("Pizza", 120.00);
        menu.put("Pasta", 40.80);
        menu.put("Soup", 10.50);
        menu.put("Smoothie", 30.00);
        menu.put("Fruit Bowl", 25.50);
    }

    private void showMenuDialog() {
        String[] menuItems = menu.keySet().toArray(new String[0]);
        String selectedItem = (String) JOptionPane.showInputDialog(
                this,
                "Select an item:",
                "Add Item",
                JOptionPane.QUESTION_MESSAGE,
                null,
                menuItems,
                menuItems[0]);

        if (selectedItem != null) {
            double itemPrice = menu.get(selectedItem);
            addToOrder(selectedItem, itemPrice);
        }
    }

    private void addToOrder(String itemName, double itemPrice) {
        totalPrice += itemPrice;
        totalPriceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));

        // Add a heading for the selected items if it's not already present
        if (!selectedItemsTextArea.getText().contains("Selected Items:")) {
            selectedItemsTextArea.setText("Selected Items:\n");
        }

        // Append the selected item and its price
        selectedItemsTextArea.append(itemName + " - $" + String.format("%.2f", itemPrice) + "\n");

        // Append the total price as well
        selectedItemsTextArea.append("Total Price: $" + String.format("%.2f", totalPrice) + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CafeShop cafeShopApp = new CafeShop();
            cafeShopApp.setVisible(true);
        });
    }
}