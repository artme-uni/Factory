package ru.nsu.g.akononov.factory.factory.maketing;

import ru.nsu.g.akononov.factory.factory.car.Car;
import ru.nsu.g.akononov.factory.factory.observable.Observable;
import ru.nsu.g.akononov.factory.factory.observable.operation;

import java.util.LinkedList;
import java.util.logging.Logger;

public class CarDealer extends Observable {

    private final Thread thread;
    private final Logger logger;
    private static int soldCarsCount;

    public CarDealer(LinkedList<Car> storage, Logger logger) {
        this.logger = logger;

        this.thread = new Thread(() -> {
            while (true) {
                synchronized (storage) {
                    while (storage.isEmpty()) {
                        try {
                            storage.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Car car = storage.poll();

                    notifyObserver(operation.carsCount, storage.size(), 0);
                    soldCarsCount++;
                    notifyObserver(operation.soldCarsCount, soldCarsCount, 0);

                    logger.info(car.toString());
                    storage.notify();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        start();
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}