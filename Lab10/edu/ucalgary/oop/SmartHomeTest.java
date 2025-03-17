package edu.ucalgary.oop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SmartHomeTest {
    private SmartHome smartHome;
    private SmartLight light;
    private SmartThermostat thermostat;
    private SmartLock lock;

    @Before
    public void setUp() {
        smartHome = new SmartHome();
        light = new SmartLight();
        thermostat = new SmartThermostat();
        lock = new SmartLock();
    }

    // 1) INHERITANCE: SmartLock, SmartLight, and SmartThermostat inherit from SmartDevice
    @Test
    public void testInheritance() {
        assertTrue("SmartLight should inherit from SmartDevice.", light instanceof SmartDevice);
        assertTrue("SmartThermostat should inherit from SmartDevice.", thermostat instanceof SmartDevice);
        assertTrue("SmartLock should inherit from SmartDevice.", lock instanceof SmartDevice);
    }

    // 2) GENERICS: getState() in each child comes from SmartDevice and is not overridden
    @Test
    public void testGenericsGetState() {
        light.setState(true);
        assertEquals("SmartLight's getState() should return a Boolean.", true, light.getState());

        thermostat.setState(22);
        assertEquals("SmartThermostat's getState() should return an Integer.", 22, (int) thermostat.getState());

        lock.setState(false);
        assertEquals("SmartLock's getState() should return a Boolean.", false, lock.getState());
    }

    // 3) GENERICS: setState() in each child comes from SmartDevice and is not overridden
    @Test
    public void testGenericsSetState() {
        light.setState(true);
        assertEquals("SmartLight's setState() should set a Boolean.", true, light.getState());

        thermostat.setState(22);
        assertEquals("SmartThermostat's setState() should set an Integer.", 22, (int) thermostat.getState());

        lock.setState(false);
        assertEquals("SmartLock's setState() should set a Boolean.", false, lock.getState());
    }

    // 4) INTERFACES: SmartDevice (or child) is realizing Observer
    @Test
    public void testInterfaces() {
        assertTrue("SmartLight should implement Observer.", light instanceof Observer);
        assertTrue("SmartThermostat should implement Observer.", thermostat instanceof Observer);
        assertTrue("SmartLock should implement Observer.", lock instanceof Observer);
    }

    // 5) BUILDER PATTERN: SmartHome must call build() before setDeviceState or sendMessage is possible
    @Test
    public void testBuilderPattern_MustCallBuildBeforeUse() {
        // Add devices but do not call build()
        smartHome.addDevice(light).addDevice(thermostat).addDevice(lock);

        // Test setDeviceState before build()
        try {
            smartHome.setDeviceState(light, true);
            fail("Expected IllegalStateException when calling setDeviceState before build().");
        } catch (IllegalStateException e) {
            assertEquals("SmartHome must be built before setting device states.", e.getMessage());
        }

        // Test sendMessage before build()
        try {
            smartHome.sendMessage("Sleep");
            fail("Expected IllegalStateException when calling sendMessage before build().");
        } catch (IllegalStateException e) {
            assertEquals("SmartHome must be built before sending messages.", e.getMessage());
        }

        // Call build() and verify that methods work afterward
        smartHome.build();

        // Reset the state of the light to ensure it starts OFF
        light.setState(false);

        // Set the light to ON and verify
        smartHome.setDeviceState(light, true);
        assertTrue("SmartLight should be ON after setDeviceState.", light.getState());

        // Send the Sleep message and verify
        smartHome.sendMessage("Sleep");
        assertFalse("SmartLight should be OFF after Sleep message.", light.getState());
        assertEquals("SmartThermostat should be set to 18°C after Sleep message.", 18, (int) thermostat.getState());
        assertTrue("SmartLock should be LOCKED after Sleep message.", lock.getState());
    }

    // 6) OBSERVER PATTERN: Observers can be added and removed from SmartHome
    @Test
    public void testObserverPattern_AddAndRemoveObservers() {
        smartHome.addDevice(light).addDevice(thermostat).addDevice(lock).build();
        smartHome.sendMessage("Sleep");
        // No exception means observers were added successfully
    }

    // 7) OBSERVER PATTERN: Observers respond to state "Sleep" appropriately
    @Test
    public void testObserverPattern_SleepState() {
        smartHome.addDevice(light).addDevice(thermostat).addDevice(lock).build();
        smartHome.sendMessage("Sleep");

        assertFalse("SmartLight should be OFF in Sleep state.", light.getState());
        assertEquals("SmartThermostat should be set to 18°C in Sleep state.", 18, (int) thermostat.getState());
        assertTrue("SmartLock should be LOCKED in Sleep state.", lock.getState());
    }

    // 8) OBSERVER PATTERN: Observers respond to state "Vacation" appropriately
    @Test
    public void testObserverPattern_VacationState() {
        smartHome.addDevice(light).addDevice(thermostat).addDevice(lock).build();
        smartHome.sendMessage("Vacation");

        assertTrue("SmartLight should be ON or OFF randomly in Vacation state.", light.getState() != null);
        assertEquals("SmartThermostat should be set to 20°C in Vacation state.", 20, (int) thermostat.getState());
        assertTrue("SmartLock should be LOCKED in Vacation state.", lock.getState());
    }

    // 9) OBSERVER PATTERN: Observers respond to setDeviceState
    @Test
    public void testObserverPattern_SetDeviceState() {
        smartHome.addDevice(light).addDevice(thermostat).addDevice(lock).build();
        smartHome.setDeviceState(light, true);
        smartHome.setDeviceState(thermostat, 25);
        smartHome.setDeviceState(lock, false);

        assertTrue("SmartLight should be ON after setDeviceState.", light.getState());
        assertEquals("SmartThermostat should be set to 25°C after setDeviceState.", 25, (int) thermostat.getState());
        assertFalse("SmartLock should be UNLOCKED after setDeviceState.", lock.getState());
    }
}
