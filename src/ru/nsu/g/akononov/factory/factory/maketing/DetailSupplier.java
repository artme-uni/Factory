package ru.nsu.g.akononov.factory.factory.maketing;

import ru.nsu.g.akononov.factory.factory.car.Detail;
import ru.nsu.g.akononov.factory.factory.observable.Observable;
import ru.nsu.g.akononov.factory.factory.observable.operation;

import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Supplier;

public class DetailSupplier<T extends Detail> extends Observable {

    private final Thread thread;

    public DetailSupplier(int storageCapacity, LinkedList<T> storage, Supplier<T> supplier, int delay) {

        this.thread = new Thread(() -> {
            while (true) {
                synchronized (storage) {
                    while (storage.size() == storageCapacity) {
                        try {
                            storage.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    T detail = supplier.get();
                    storage.add(detail);
                    storage.notify();

                    notifyObserver(Objects.requireNonNull(detail.getOperation()), storage.size(), 0);
                }

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void start() {
        thread.start();
    }

    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
