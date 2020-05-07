package ru.nsu.g.akononov.factory.factory;

import ru.nsu.g.akononov.factory.car.Accessory;
import ru.nsu.g.akononov.factory.car.Body;
import ru.nsu.g.akononov.factory.car.Engine;

import java.util.LinkedList;

public class Suppliers {

    final private DetailSupplier<Body> bodySupplier;
    final private DetailSupplier<Engine> engineSupplier;
    LinkedList<DetailSupplier<Accessory>> accessorySuppliers = new LinkedList<>();
    final private int accessoriesSuppliersCount;

    public Suppliers(Storage storage, int accessoriesSuppliersCount)
    {
        bodySupplier = new DetailSupplier<>(storage.getBodiesStorageCapacity(), storage.getBodiesStorage(), Body::new);
        engineSupplier = new DetailSupplier<>(storage.getEnginesStorageCapacity(), storage.getEnginesStorage(), Engine::new);

        for (int i = 0; i < accessoriesSuppliersCount; i++) {
            accessorySuppliers.add(new DetailSupplier<>(storage.getAccessoriesStorageCapacity(), storage.getAccessoriesStorage(), Accessory::new));
        }

        this.accessoriesSuppliersCount = accessoriesSuppliersCount;

        startWork();
    }

    private void startWork()
    {
        for (int i = 0; i < accessoriesSuppliersCount; i++) {
            accessorySuppliers.get(i).start();
        }

        bodySupplier.start();
        engineSupplier.start();
    }

    private void stopWork()
    {
        for (int i = 0; i < accessoriesSuppliersCount; i++) {
            accessorySuppliers.get(i).stop();
        }

        bodySupplier.stop();
        engineSupplier.stop();
    }
}
