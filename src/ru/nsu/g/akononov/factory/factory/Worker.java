package ru.nsu.g.akononov.factory.factory;

import ru.nsu.g.akononov.factory.car.*;

import java.util.LinkedList;

public class Worker {

    private final Storage storage;

    Worker (Storage storage)
    {
        this.storage = storage;
    }

    public Car makeCar()
    {
        Engine engine = (Engine) consumeDetail(storage.getEnginesStorage());
        Accessory accessory = (Accessory) consumeDetail(storage.getAccessoriesStorage());
        Body body = (Body) consumeDetail(storage.getBodiesStorage());

        Car car = new Car(body, engine, accessory);
        System.out.println(car);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
            detail = storage.poll();
            System.out.println("-\t" + detail);
            storage.notify();
        }

        return detail;
    }

}
