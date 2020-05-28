package ru.nsu.g.akononov.factory.components;

import ru.nsu.g.akononov.factory.components.car.Accessory;
import ru.nsu.g.akononov.factory.components.car.Body;
import ru.nsu.g.akononov.factory.components.car.Engine;
import ru.nsu.g.akononov.factory.components.maketing.DetailSupplier;
import ru.nsu.g.akononov.factory.components.observable.Observer;

import java.util.LinkedList;

public class Suppliers {

    final public DetailSupplier<Body> bodySupplier;
    final public DetailSupplier<Engine> engineSupplier;
    final public LinkedList<DetailSupplier<Accessory>> accessorySuppliers = new LinkedList<>();
    final private int accessoriesSuppliersCount;

    public Suppliers(Storage storage, int accessoriesSuppliersCount, int bodyDelay, int engineDelay, int accessoryDelay)
    {
        bodySupplier = new DetailSupplier<>(storage.getBodiesStorageCapacity(), storage.getBodiesStorage(), Body::new, bodyDelay);
        engineSupplier = new DetailSupplier<>(storage.getEnginesStorageCapacity(), storage.getEnginesStorage(), Engine::new, engineDelay);

        for (int i = 0; i < accessoriesSuppliersCount; i++) {
            accessorySuppliers.add(new DetailSupplier<>(storage.getAccessoriesStorageCapacity(),
                    storage.getAccessoriesStorage(), Accessory::new, accessoryDelay));
        }

        this.accessoriesSuppliersCount = accessoriesSuppliersCount;

        start();
    }

    public void registerObserver(Observer observer)
    {
        bodySupplier.registerObserver(observer);
        engineSupplier.registerObserver(observer);
        for (var accessorySupplier : accessorySuppliers)
        {
            accessorySupplier.registerObserver(observer);
        }
    }

    private void start()
    {
        for (int i = 0; i < accessoriesSuppliersCount; i++) {
            accessorySuppliers.get(i).start();
        }

        bodySupplier.start();
        engineSupplier.start();
    }

    public void shutdown()
    {
        for (int i = 0; i < accessoriesSuppliersCount; i++) {
            accessorySuppliers.get(i).stop();
        }

        bodySupplier.stop();
        engineSupplier.stop();
    }
}
