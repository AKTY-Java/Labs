package edu.ucalgary.oop;

public abstract class SmartDevice<T> implements Observer {
    private T state;

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
        performAction();
    }

    protected abstract void performAction();
}
