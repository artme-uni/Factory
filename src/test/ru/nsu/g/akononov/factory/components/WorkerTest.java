package ru.nsu.g.akononov.factory.components;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.nsu.g.akononov.factory.components.car.*;

import static org.junit.Assert.*;

public class WorkerTest {
    @Test
    public void getBodiesStorage() {

        Storage storage = new Storage(10, 10, 10, 10);

        Car car = new Car(new Body(), new Engine(), new Accessory());

        storage.getBodiesStorage().add(new Body());
        storage.getEnginesStorage().add(new Engine());
        storage.getAccessoriesStorage().add(new Accessory());

        Worker worker = new Worker(storage, 1);

        try {
            car = worker.makeCar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Car:2(Body:2,Engine:2,Accessory:2)", car.toString());
    }

    @BeforeClass
    public static void afterClass() {
        Detail.serialNumbers.put("Body", 0);
        Detail.serialNumbers.put("Engine", 0);
        Detail.serialNumbers.put("Accessory", 0);
        Detail.serialNumbers.put("Car", 0);
    }
}