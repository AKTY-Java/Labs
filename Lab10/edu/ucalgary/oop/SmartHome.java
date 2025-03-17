package edu.ucalgary.oop;
import java.util.ArrayList;
import java.util.List;

public class SmartHome {
    private List<SmartDevice<?>> devices = new ArrayList<>();
    private boolean isBuilt = false;

    // Add a device (allowed before build)
    public SmartHome addDevice(SmartDevice<?> device) {
        // TODO: Implement addDevice functionality
    }

    // Build the SmartHome (finalizes the setup)
    public SmartHome build() {
        // TODO: Implement build functionality
    }

    // Set device state (only allowed after build)
    public <T> void setDeviceState(SmartDevice<T> device, T state) {
        // TODO: Implement setDeviceState functionality
    }

    // Send overarching messages (only allowed after build)
    public void sendMessage(String message) {
        // TODO: Implement sendMessage functionality
    }
}
