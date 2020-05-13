package ru.nsu.g.akononov.factory.components;

import ru.nsu.g.akononov.factory.components.observable.Observable;
import ru.nsu.g.akononov.factory.components.observable.Observer;
import ru.nsu.g.akononov.factory.components.observable.operation;
import ru.nsu.g.akononov.factory.threadPool.ThreadPool;

public class Manager extends Observable {

    private final Thread thread;

    public Manager(ThreadPool workers, Storage storage, Observer ui){

        this.thread = new Thread(() -> {
            outer : while (true) {
                synchronized (storage.getCarsStorage()) {

                    while (storage.getCarsStorage().size() + workers.getTaskCount() >= storage.getCarsStorageCapacity()) {
                        try {
                            storage.getCarsStorage().wait();
                        } catch (InterruptedException e) {
                            //System.out.println("Manager " + Thread.currentThread().getId() + " go sleep");
                            break outer;
                        }
                    }

                    int diff = storage.getCarsStorageCapacity() - storage.getCarsStorage().size() - workers.getTaskCount();

                    for (int i = 0; i < diff; i++) {
                        workers.execute(() -> {
                            synchronized (storage.getCarsStorage()) {
                                Worker worker = new Worker(storage, workers.getOrderNumber((int) Thread.currentThread().getId()));
                                worker.registerObserver(ui);

                                storage.getCarsStorage().add(worker.makeCar());

                                storage.getCarsStorage().notify();

                                notifyObserver(operation.carsCount, storage.getCarsStorage().size(), 0);
                            }
                        });
                    }
                }
            }
        });

        start();

    }

    public void start() {
        thread.start();
    }

    public void shutdown() {

        thread.interrupt();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
