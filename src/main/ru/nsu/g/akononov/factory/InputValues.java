package ru.nsu.g.akononov.factory;

import java.util.Properties;

public class InputValues {
    final public int bodiesStorageCapacity;
    final public int engineStorageCapacity;
    final public int accessoriesStorageCapacity;
    final public int carsStorageCapacity;

    final public int accessoriesSuppliersCount;
    final public int workersCount;
    final public int dealersCount;

    final public int bodySupplierDelay;
    final public int engineSupplierDelay;
    final public int accessorySupplierDelay;
    final public int dealerDelay;

    final public boolean isLogging;


    public InputValues(Properties initValues) {
        bodiesStorageCapacity = Integer.parseInt(initValues.getProperty("BodiesStorageCapacity"));
        engineStorageCapacity = Integer.parseInt(initValues.getProperty("EnginesStorageCapacity"));
        accessoriesStorageCapacity = Integer.parseInt(initValues.getProperty("AccessoriesStorageCapacity"));
        carsStorageCapacity = Integer.parseInt(initValues.getProperty("CarsStorageCapacity"));

        accessoriesSuppliersCount = Integer.parseInt(initValues.getProperty("SuppliersCount"));
        workersCount = Integer.parseInt(initValues.getProperty("WorkersCount"));
        dealersCount = Integer.parseInt(initValues.getProperty("DealersCount"));

        bodySupplierDelay = Integer.parseInt(initValues.getProperty("BodySupplierDelay"));
        engineSupplierDelay = Integer.parseInt(initValues.getProperty("EngineSupplierDelay"));
        accessorySupplierDelay = Integer.parseInt(initValues.getProperty("AccessorySupplierDelay"));
        dealerDelay = Integer.parseInt(initValues.getProperty("DealerDelay"));

        isLogging = Boolean.parseBoolean(initValues.getProperty("LogSale"));
    }
}
