package ru.nsu.g.akononov.factory;

import ru.nsu.g.akononov.factory.factory.Manager;
import ru.nsu.g.akononov.factory.factory.Storage;
import ru.nsu.g.akononov.factory.factory.Suppliers;
import ru.nsu.g.akononov.factory.threadPool.ThreadPool;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        File file = new File("src/ru/nsu/g/akononov/factory/data.properties");
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


        Storage storage = new Storage(bodiesStorageCapacity, engineStorageCapacity, accessoriesStorageCapacity, carsStorageCapacity);
        Suppliers suppliers = new Suppliers(storage, accessoriesSuppliersCount);
        ThreadPool workers = new ThreadPool(workersCount);
        Manager manager = new Manager(workers, storage);
    }
}