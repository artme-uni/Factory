package ru.nsu.g.akononov.factory.components.car;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void getName() {
        Engine engine = new Engine();
        assertEquals("Engine:1", engine.toString());
    }

    @Test
    public void serialNumber() {
        Body body1 = new Body();
        Body body2 = new Body();

        assertEquals("Body:2", body2.toString());
    }

    @Test
    public void createCar() {
        Engine engine = new Engine();
        Body body = new Body();
        Accessory accessory = new Accessory();

        Car car = new Car(body, engine, accessory);

        assertEquals("Car:1(Body:3,Engine:2,Accessory:1)", car.toString());
    }

    @BeforeClass
    public static void afterClass() {
        Detail.serialNumbers.put("Body", 0);
        Detail.serialNumbers.put("Engine", 0);
        Detail.serialNumbers.put("Accessory", 0);
        Detail.serialNumbers.put("Car", 0);
    }
}