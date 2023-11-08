package com.arcane.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Here we can see the trade-off of using this superclass vs.
    // embedding this functionality in the GameBoard itself. We have
    // to make the object passed in the notify generic (Object). At least
    // if we use the pull model.

    // Also note that this method is protected, meaning that only sublasses
    // can call this method. We don't want the rest of the world to be able
    // to call this method on the subject. Only the subject knows when it is
    // appropriate to call this method.
//    protected void notifyObservers() {
//        for (Observer observer : observers) {
//            observer.notifyOfChange(this);
//        }
//    }

    // Here's the pull model, with nothing passed:
    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.notifyOfChange();
        }
    }
}
