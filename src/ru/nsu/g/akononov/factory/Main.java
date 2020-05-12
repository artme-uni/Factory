package ru.nsu.g.akononov.factory;

import ru.nsu.g.akononov.factory.factory.*;
import ru.nsu.g.akononov.factory.factory.maketing.SalesLogger;
import ru.nsu.g.akononov.factory.threadPool.ThreadPool;
import ru.nsu.g.akononov.factory.view.UI;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        File file = new File("src/resources/data.properties");
        Properties initValues = new Properties();
        try {
            initValues.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int bodiesStorageCapacity = Integer.parseInt(initValues.getProperty("BodiesStorageCapacity"));
        int engineStorageCapacity = Integer.parseInt(initValues.getProperty("EnginesStorageCapacity"));
        int accessoriesStorageCapacity = Integer.parseInt(initValues.getProperty("AccessoriesStorageCapacity"));
        int carsStorageCapacity = Integer.parseInt(initValues.getProperty("CarsStorageCapacity"));

        int accessoriesSuppliersCount = Integer.parseInt(initValues.getProperty("SuppliersCount"));
        int workersCount = Integer.parseInt(initValues.getProperty("WorkersCount"));
        int dealersCount = Integer.parseInt(initValues.getProperty("DealersCount"));

        int bodySupplierDelay = Integer.parseInt(initValues.getProperty("BodySupplierDelay"));
        int engineSupplierDelay = Integer.parseInt(initValues.getProperty("EngineSupplierDelay"));
        int accessorySupplierDelay = Integer.parseInt(initValues.getProperty("AccessorySupplierDelay"));
        int dealerDelay = Integer.parseInt(initValues.getProperty("DealerDelay"));

        boolean isLogging = Boolean.parseBoolean(initValues.getProperty("LogSale"));


        Storage storage = new Storage(bodiesStorageCapacity, engineStorageCapacity, accessoriesStorageCapacity, carsStorageCapacity);
        Suppliers suppliers = new Suppliers(storage, accessoriesSuppliersCount, bodySupplierDelay, engineSupplierDelay, accessorySupplierDelay);
        ThreadPool workers = new ThreadPool(workersCount);

        UI ui = new UI(storage, workersCount);
        Manager manager = new Manager(workers, storage, ui);

        SalesLogger logger = new SalesLogger(isLogging);
        Dealers dealers = new Dealers(dealersCount, storage.getCarsStorage(), logger, dealerDelay);

        dealers.registerObserver(ui);
        suppliers.registerObserver(ui);
    }
}