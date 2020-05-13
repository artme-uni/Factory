package ru.nsu.g.akononov.factory.components;

import ru.nsu.g.akononov.factory.components.car.Accessory;
import ru.nsu.g.akononov.factory.components.car.Body;
import ru.nsu.g.akononov.factory.components.car.Car;
import ru.nsu.g.akononov.factory.components.car.Engine;

import java.util.LinkedList;

public class Storage {
    final private LinkedList<Body> bodiesStorage = new LinkedList<>();
    final private LinkedList<Engine> enginesStorage = new LinkedList<>();
    final private LinkedList<Accessory> accessoriesStorage = new LinkedList<>();
    final private LinkedList<Car> carsStorage = new LinkedList<>();

    final private int bodiesStorageCapacity;
    final private int enginesStorageCapacity;
    final private int accessoriesStorageCapacity;
    final private int carsStorageCapacity;

    public Storage(int bodiesStorageCapacity, int enginesStorageCapacity, int accessoriesStorageCapacity, int carsStorageCapacity) {
        this.bodiesStorageCapacity = bodiesStorageCapacity;
        this.enginesStorageCapacity = enginesStorageCapacity;
        this.accessoriesStorageCapacity = accessoriesStorageCapacity;
        this.carsStorageCapacity = carsStorageCapacity;
    }

    public LinkedList<Body> getBodiesStorage() {
        return bodiesStorage;
    }

    public LinkedList<Engine> getEnginesStorage() {
        return enginesStorage;
    }

    public LinkedList<Accessory> getAccessoriesStorage() {
        return accessoriesStorage;
    }

    public LinkedList<Car> getCarsStorage() {
        return carsStorage;
    }

    public int getBodiesStorageCapacity() {
        return bodiesStorageCapacity;
    }

    public int getEnginesStorageCapacity() {
        return enginesStorageCapacity;
    }

    public int getAccessoriesStorageCapacity() {
        return accessoriesStorageCapacity;
    }

    public int getCarsStorageCapacity() {
        return carsStorageCapacity;
    }
}
