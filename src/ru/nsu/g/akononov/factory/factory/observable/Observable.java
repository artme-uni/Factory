package ru.nsu.g.akononov.factory.factory.observable;

import java.util.ArrayList;

public abstract class Observable {

    private ArrayList<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        if (observer == null) {
            throw new NullPointerException();
        }
        if (observers.contains(observer)) {
            throw new IllegalArgumentException("Repeated observer:" + observer);
        }

        observers.add(observer);
    }

    public void notifyObserver(operation operation, int argument, int status) {

        switch (operation) {
            case bodiesCount: {
                for (Observer observer : observers) {
                    observer.updateBodiesCount(argument);
                }
                break;
            }
            case enginesCount: {
                for (Observer observer : observers) {
                    observer.updateEnginesCount(argument);
                }
                break;
            }
            case accessoriesCount: {
                for (Observer observer : observers) {
                    observer.updateAccessoriesCount(argument);
                }
                break;
            }
            case workersStatus: {
                for (Observer observer : observers) {
                    observer.updateWorkersStatus(argument, status);
                }
                break;
            }
            case carsCount: {
                for (Observer observer : observers) {
                    observer.updateCarsCount(argument);
                }
                break;
            }
            case soldCarsCount: {
                for (Observer observer : observers) {
                    observer.updateSoldCarsCount(argument);
                }
                break;
            }
        }
    }
}
