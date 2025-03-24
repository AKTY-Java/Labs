package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SmartHomeGUI {
    public static void main(String[] args) {
        // Create devices
        SmartLight light = new SmartLight();
        SmartThermostat thermostat = new SmartThermostat();
        SmartLock lock = new SmartLock();

        // Create and build SmartHome
        SmartHome smartHome = new SmartHome()
                .addDevice(light)
                .addDevice(thermostat)
                .addDevice(lock)
                .build();

        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
