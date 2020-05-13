package ru.nsu.g.akononov.factory.components.maketing;

import org.junit.Test;
import ru.nsu.g.akononov.factory.components.car.Accessory;
import ru.nsu.g.akononov.factory.components.car.Body;
import ru.nsu.g.akononov.factory.components.car.Car;
import ru.nsu.g.akononov.factory.components.car.Engine;

import java.util.LinkedList;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class CarDealerTest {
    @Test
    public void consumeCar() {
        LinkedList<Car> carStorage = new LinkedList<>();
        carStorage.add(new Car(new Body(), new Engine(), new Accessory()));
        carStorage.add(new Car(new Body(), new Engine(), new Accessory()));

        CarDealer carDealer = new CarDealer(carStorage, new SalesLogger(false).getLogger(), 0);

        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(0, carStorage.size());

        carDealer.stop();
    }

}