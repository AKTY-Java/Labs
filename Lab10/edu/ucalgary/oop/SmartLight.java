// TODO: package, any includes, class declaration, constructor
//       and the methods performAction and update.
package edu.ucalgary.oop;


public class SmartLight extends SmartDevice<Boolean> {

    public SmartLight() {
        super();
        setState(false);
    }

    @Override
    public void update(String message) {
        switch (message) {
            case "Sleep":
                setState(false);
                performAction();
                break;

            case "Vacation":
                boolean newState = Math.random() < 0.5; // Randomly turn ON/OFF
                setState(newState);
                System.out.println("SmartLight is " + (newState ? "ON. Brighting the room." : "OFF. Room is dark. "));
                break;
            default:
                System.out.println("SmartLight: Unknown message - " + message);
        }
    }



    public void dim(int percentage) {
        if (getState()) {
            System.out.println("Dimming the light to " + percentage + "%.");
        } else {
            System.out.println("Cannot dim the light. It is currently OFF.");
        }
    }



    public void performAction() {
        if (!getState()) {
            System.out.print("SmartLight is OFF. ");
            System.out.print("Room is dark");
        } else {
            System.out.print("SmartLight is ON.");
            System.out.print("Brightening the room.");
        }
    }
}
