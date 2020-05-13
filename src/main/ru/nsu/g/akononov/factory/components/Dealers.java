package ru.nsu.g.akononov.factory.components;

import ru.nsu.g.akononov.factory.components.car.Car;
import ru.nsu.g.akononov.factory.components.maketing.CarDealer;
import ru.nsu.g.akononov.factory.components.maketing.SalesLogger;
import ru.nsu.g.akononov.factory.components.observable.Observer;

import java.util.LinkedList;

public class Dealers {
    private final LinkedList<CarDealer> dealers;

    public Dealers(int dealersCount, LinkedList<Car> storage, SalesLogger logger, int delay)
    {
        dealers = new LinkedList<>();
        SalesLogger salesLogger = logger;

        for (int i = 0; i < dealersCount; i++) {
            dealers.add(new CarDealer(storage, salesLogger.getLogger(), delay));
        }
    }

    public void registerObserver(Observer observer)
    {
        for (var dealer : dealers)
        {
            dealer.registerObserver(observer);
        }
    }

    public void shutdown()
    {
        for (var dealer : dealers)
        {
            dealer.stop();
        }
    }
}
