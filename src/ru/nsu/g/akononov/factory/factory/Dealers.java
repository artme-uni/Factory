package ru.nsu.g.akononov.factory.factory;

import ru.nsu.g.akononov.factory.factory.car.Car;
import ru.nsu.g.akononov.factory.factory.maketing.CarDealer;
import ru.nsu.g.akononov.factory.factory.maketing.SalesLogger;
import ru.nsu.g.akononov.factory.factory.observable.Observer;

import java.util.LinkedList;

public class Dealers {
    private final LinkedList<CarDealer> dealers;

    public Dealers(int dealersCount, LinkedList<Car> storage)
    {
        dealers = new LinkedList<>();
        SalesLogger salesLogger = new SalesLogger();

        for (int i = 0; i < dealersCount; i++) {
            dealers.add(new CarDealer(storage, salesLogger.getLogger()));
        }
    }

    public void registerObserver(Observer observer)
    {
        for (var dealer : dealers)
        {
            dealer.registerObserver(observer);
        }
    }
}
