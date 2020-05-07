package ru.nsu.g.akononov.factory.factory;

import ru.nsu.g.akononov.factory.factory.observable.Observable;
import ru.nsu.g.akononov.factory.factory.observable.Observer;
import ru.nsu.g.akononov.factory.factory.observable.operation;
import ru.nsu.g.akononov.factory.threadPool.ThreadPool;

public class Manager extends Observable {

    private final Thread thread;

    public Manager(ThreadPool workers, Storage storage, Observer ui){
        registerObserver(ui);

        this.thread = new Thread(() -> {
            while (true) {
                synchronized (storage.getCarsStorage()) {

                    while (storage.getCarsStorage().size() + workers.getTaskCount() >= storage.getCarsStorageCapacity()) {
                        try {
                            storage.getCarsStorage().wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    int diff = storage.getCarsStorageCapacity() - storage.getCarsStorage().size() - workers.getTaskCount();

                    for (int i = 0; i < diff; i++) {
                        workers.execute(() -> {
                            synchronized (storage.getCarsStorage()) {
                                Worker worker = new Worker(storage, ui, workers.getOrderNumber((int) Thread.currentThread().getId()));
                                storage.getCarsStorage().add(worker.makeCar());
                                storage.getCarsStorage().notify();

                                notifyObserver(operation.carsCount, storage.getCarsStorage().size(), 0);
                            }
                        });
                    }
                }
            }
        });

        startWork();

    }

    public void startWork() {
        thread.start();
    }

    public void stopWork() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
