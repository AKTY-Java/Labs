package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SmartHomeGUI extends JFrame implements ActionListener {

    private JLabel thermostatLabel;
    private JTextField thermostatInput;
    private JButton setTemperatureButton;

    private int thermostatTemp = 20;

    public SmartHomeGUI() {
        setTitle("Smart Thermostat");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Smart Thermostat"));


        thermostatLabel = new JLabel("Thermostat set to: " + thermostatTemp + "°C");
        thermostatInput = new JTextField(5);
        setTemperatureButton = new JButton("Set Temperature");

        setTemperatureButton.addActionListener(this);

        panel.add(thermostatLabel);
        panel.add(new JLabel("Enter new temperature:"));
        panel.add(thermostatInput);
        panel.add(setTemperatureButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == setTemperatureButton) {
            
                int newTemp = Integer.parseInt(thermostatInput.getText());
                thermostatTemp = newTemp;
                thermostatLabel.setText("Current Temperature: " + thermostatTemp + "°C");
                JOptionPane.showMessageDialog(this, "Thermostat set to " + thermostatTemp + "°C");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new SmartHomeGUI().setVisible(true);        
        });
    }
}