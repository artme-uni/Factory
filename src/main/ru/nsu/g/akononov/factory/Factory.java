package ru.nsu.g.akononov.factory;

import ru.nsu.g.akononov.factory.components.Dealers;
import ru.nsu.g.akononov.factory.components.Manager;
import ru.nsu.g.akononov.factory.components.Storage;
import ru.nsu.g.akononov.factory.components.Suppliers;
import ru.nsu.g.akononov.factory.components.maketing.SalesLogger;
import ru.nsu.g.akononov.factory.threadPool.ThreadPool;
import ru.nsu.g.akononov.factory.view.GUI;

public class Factory {

    private static Suppliers suppliers;
    private static ThreadPool workers;
    private static Manager manager;
    private static Dealers dealers;

    Factory(InputValues i) {
        start(i);
    }

    public void setBodySupplierDelay(int delay) {
        suppliers.bodySupplier.setDelay(delay);
    }

    public void setEngineSupplierDelay(int delay) {
        suppliers.engineSupplier.setDelay(delay);
    }

    public void setDealersDelay(int delay) {
        dealers.setDelay(delay);
    }

    public void setAccessoriesSuppliersDelay(int delay) {
        for (var supplier : suppliers.accessorySuppliers) {
            supplier.setDelay(delay);
        }
    }

    private void start(InputValues i) {
        System.out.println("Factory is executed!");
        Storage storage = new Storage(i.bodiesStorageCapacity, i.engineStorageCapacity, i.accessoriesStorageCapacity, i.carsStorageCapacity);
        suppliers = new Suppliers(storage, i.accessoriesSuppliersCount, i.bodySupplierDelay, i.engineSupplierDelay, i.accessorySupplierDelay);
        workers = new ThreadPool(i.workersCount);

        SalesLogger logger = new SalesLogger(i.isLogging);
        dealers = new Dealers(i.dealersCount, storage.getCarsStorage(), logger, i.dealerDelay);

        GUI ui = new GUI(storage, i.workersCount, this, i);

        manager = new Manager(workers, storage, ui);

        dealers.registerObserver(ui);
        suppliers.registerObserver(ui);
        manager.registerObserver(ui);
    }

    public void shutdown() {
        System.out.println("Waiting for factory shutdown...");
        dealers.shutdown();
        manager.shutdown();
        workers.shutdown();
        suppliers.shutdown();
        System.out.println("Factory is shutdown!");
    }
}
