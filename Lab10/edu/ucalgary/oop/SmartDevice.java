package edu.ucalgary.oop;

public abstract class SmartDevice<T> implements Observer {
    private T state;

    // TODO: Complete the rest of the class
    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }
}
