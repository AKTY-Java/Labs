package edu.ucalgary.oop;
import java.util.ArrayList;
import java.util.List;

public class SmartHome {
    private List<SmartDevice<?>> devices = new ArrayList<>();
    private boolean isBuilt = false;

    // Add a device (allowed before build)
    public SmartHome addDevice(SmartDevice<?> device) {
        devices.add(device);
        return this;
    }

    // Build the SmartHome (finalizes the setup)
    public SmartHome build() {
        isBuilt = true;
        return this;
    }

    // Set device state (only allowed after build)
    public <T> void setDeviceState(SmartDevice<T> device, T state) throws IllegalStateException {
        if (isBuilt) {
            for (SmartDevice dev : devices) {
                if (device == dev) {
                    device.setState(state);
                }
            }
        } else {
            throw new IllegalStateException("SmartHome must be built before setting device states.");
        }
    }

    // Send overarching messages (only allowed after build)
    public void sendMessage(String message) throws IllegalStateException {
        if (isBuilt) {
            for (SmartDevice device : devices) {
                device.update(message);
            }
        } else {
            throw new IllegalStateException("SmartHome must be built before sending messages.");
        }
    }
}
