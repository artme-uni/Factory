package ru.nsu.g.akononov.factory.factory;

import ru.nsu.g.akononov.factory.threadPool.ThreadPool;

public class Manager {

    private final Thread thread;

    public Manager(ThreadPool workers, Storage storage) {

        this.thread = new Thread(() -> {
            while (true) {
                synchronized (storage.getCarsStorage()) {
                    while (storage.getCarsStorage().size() == storage.getCarsStorageCapacity()) {
                        try {
                            storage.getCarsStorage().wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    workers.execute(() -> {
                        synchronized (storage.getCarsStorage()) {
                            Worker worker = new Worker(storage);
                            storage.getCarsStorage().add(worker.makeCar());
                        }
                    });

                    System.out.println("Car storage : " + storage.getCarsStorage().size() + "\\" + storage.getCarsStorageCapacity());
                    storage.getCarsStorage().notify();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
