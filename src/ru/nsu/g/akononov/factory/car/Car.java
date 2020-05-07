package ru.nsu.g.akononov.factory.car;

public class Car extends Detail {
    Body body;
    Engine engine;
    Accessory accessory;

    public Car(Body body, Engine engine, Accessory accessory) {
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
    }

    @Override
    public String toString() {
        return "Car:" + serialNumber + "(" + body + "," + engine + "," + accessory + ")";
    }
}
