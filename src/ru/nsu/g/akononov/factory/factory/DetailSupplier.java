package ru.nsu.g.akononov.factory.factory;

import ru.nsu.g.akononov.factory.car.Detail;

import java.util.LinkedList;
import java.util.function.Supplier;

public class DetailSupplier<T extends Detail> {

    private final Thread thread;

    public DetailSupplier(int capacity, LinkedList<T> storage, Supplier<T> supplier) {

        this.thread = new Thread(() -> {
            while (true) {
                synchronized (storage) {
                    while (storage.size() == capacity) {
                        try {
                            storage.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    T detail = supplier.get();
                    storage.add(detail);
                    System.out.println("\t" + detail + "\t\t" + storage.size() + "\\" + capacity);
                    storage.notify();
                }

                try {
                    Thread.sleep(1000);
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
