package ru.nsu.g.akononov.factory.components.observable;

public interface Observer {
    void updateBodiesCount(int count);

    void updateEnginesCount(int count);

    void updateAccessoriesCount(int count);

    void updateWorkersStatus(int count, int workerNumber);

    void updateCarsCount(int count);

    void updateSoldCarsCount(int count);
}
