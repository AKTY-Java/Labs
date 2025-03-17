// TODO: package, any includes, class declaration, constructor
//       and the methods performAction and update.

package edu.ucalgary.oop;

public class SmartLock extends SmartDevice<Boolean> {

    public SmartLock() {
        super();
        setState(true);
    }

    @Override
    public void update(String message) {
        switch (message) {
            case "Sleep", "Vacation":
                setState(true);
                performAction();
                break;
            default:
                System.out.println("SmartLight: Unknown message - " + message);
        }
    }
    public void autoLock(int delayInSeconds) {
        System.out.println("Auto-lock enabled. Door will lock in " + delayInSeconds + " seconds.");
        new Thread(() -> {
            try {
                Thread.sleep(delayInSeconds * 1000L);
                setState(true);
                System.out.println("Door auto-locked.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void performAction() {
        if (getState()) {
            System.out.print("SmartLock is Locked. ");
            System.out.print("Securing the door. ");
        } else {
            System.out.print("SmartLock is Unlocked. ");
            System.out.print("Door is open. ");
        }
    }
}
