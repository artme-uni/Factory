package ru.nsu.g.akononov.factory.components.maketing;

import ru.nsu.g.akononov.factory.components.car.Car;
import ru.nsu.g.akononov.factory.components.observable.Observable;
import ru.nsu.g.akononov.factory.components.observable.operation;

import java.util.LinkedList;
import java.util.logging.Logger;

public class CarDealer extends Observable {

    private final Thread thread;
    private final Logger logger;
    private static int soldCarsCount = 0;
    private static final Object countLock = new Object();

    public CarDealer(LinkedList<Car> storage, Logger logger, int delay) {
        this.logger = logger;

        this.thread = new Thread(() -> {
            outer: while (true) {
                synchronized (storage) {
                    while (storage.isEmpty()) {
                        try {
                            storage.wait();
                        } catch (InterruptedException e) {
                            //System.out.println("Dealer " + Thread.currentThread().getId() + " go sleep");
                            break outer;
                        }
                    }

                    Car car = storage.poll();

                    notifyObserver(operation.carsCount, storage.size(), 0);

                    synchronized (countLock) {
                        soldCarsCount++;
                        notifyObserver(operation.soldCarsCount, soldCarsCount, 0);
                    }

                    logger.info(car.toString());
                    storage.notify();
                }

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    //System.out.println("Dealer " + Thread.currentThread().getId() + " go sleep");
                    break;
                }
            }
        });

        start();
    }

    private void start() {
        thread.start();
    }

    public void stop() {
            thread.interrupt();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
