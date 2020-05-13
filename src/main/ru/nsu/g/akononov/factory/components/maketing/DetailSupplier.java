package ru.nsu.g.akononov.factory.components.maketing;

import ru.nsu.g.akononov.factory.components.car.Detail;
import ru.nsu.g.akononov.factory.components.observable.Observable;

import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Supplier;

public class DetailSupplier<T extends Detail> extends Observable {

    private final Thread thread;

    public DetailSupplier(int storageCapacity, LinkedList<T> storage, Supplier<T> supplier, int delay) {

        this.thread = new Thread(() -> {
            outer : while (true) {
                synchronized (storage) {
                    while (storage.size() == storageCapacity) {
                        try {
                            storage.wait();
                        } catch (InterruptedException e) {
                            //System.out.println("Supplier " + Thread.currentThread().getId() + " go sleep");
                            break outer;
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
                    //System.out.println("Supplier " + Thread.currentThread().getId() + " go sleep");
                    break;
                }
            }
        });
    }


    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
