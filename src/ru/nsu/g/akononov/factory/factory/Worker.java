package ru.nsu.g.akononov.factory.factory;

import ru.nsu.g.akononov.factory.factory.car.*;
import ru.nsu.g.akononov.factory.factory.observable.Observable;
import ru.nsu.g.akononov.factory.factory.observable.Observer;
import ru.nsu.g.akononov.factory.factory.observable.operation;

import java.util.HashMap;
import java.util.LinkedList;

public class Worker extends Observable {

    private final Storage storage;
    private final int orderedNumber;

    Worker (Storage storage, Observer observer, int orderedNumber)
    {
        this.orderedNumber = orderedNumber;
        this.storage = storage;
        registerObserver(observer);
    }

    public Car makeCar()
    {
        notifyObserver(operation.workersStatus, 1, orderedNumber);

        Engine engine = (Engine) consumeDetail(storage.getEnginesStorage());
        Accessory accessory = (Accessory) consumeDetail(storage.getAccessoriesStorage());
        Body body = (Body) consumeDetail(storage.getBodiesStorage());

        Car car = new Car(body, engine, accessory);
        notifyObserver(operation.workersStatus, 0, orderedNumber);

        return car;
    }

    private Detail consumeDetail(LinkedList<? extends Detail> storage)
    {
        Detail detail;

        synchronized (storage) {
            while (storage.isEmpty()) {
                try {
                    storage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            detail = storage.pollLast();
            storage.notify();
            notifyObserver(detail.getOperation(), storage.size(), 0);
        }

        return detail;
    }

}
