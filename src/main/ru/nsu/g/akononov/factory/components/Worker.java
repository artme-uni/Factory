package ru.nsu.g.akononov.factory.components;

import ru.nsu.g.akononov.factory.components.car.*;
import ru.nsu.g.akononov.factory.components.observable.Observable;
import ru.nsu.g.akononov.factory.components.observable.Observer;
import ru.nsu.g.akononov.factory.components.observable.operation;

import java.util.LinkedList;

public class Worker extends Observable {

    private final Storage storage;
    private final int orderedNumber;

    Worker (Storage storage, int orderedNumber)
    {
        this.orderedNumber = orderedNumber;
        this.storage = storage;
    }

    public Car makeCar() throws InterruptedException {
        notifyObserver(operation.workersStatus, 1, orderedNumber);

        Engine engine = (Engine) consumeDetail(storage.getEnginesStorage());
        Accessory accessory = (Accessory) consumeDetail(storage.getAccessoriesStorage());
        Body body = (Body) consumeDetail(storage.getBodiesStorage());

        Car car = new Car(body, engine, accessory);
        notifyObserver(operation.workersStatus, 0, orderedNumber);

        return car;
    }

    private Detail consumeDetail(LinkedList<? extends Detail> storage) throws InterruptedException {
        Detail detail;

        synchronized (storage) {
            while (storage.isEmpty()) {
                    storage.wait();
            }
            detail = storage.pollLast();
            storage.notify();
            notifyObserver(detail.getOperation(), storage.size(), 0);
        }

        return detail;
    }

}
