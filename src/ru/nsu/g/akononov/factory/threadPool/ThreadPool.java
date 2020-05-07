package ru.nsu.g.akononov.factory.threadPool;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadPool {

    private final LinkedList<Runnable> taskQueue = new LinkedList<>();
    private final List<Thread> threads;
    private final HashMap<Integer, Integer> orderNumber = new HashMap<>();

    private int uncompletedTaskCount = 0;

    Runnable work = () -> {
        while (true) {
            Runnable task;

            synchronized (taskQueue) {
                while (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                task = taskQueue.poll();
            }
                task.run();
                uncompletedTaskCount--;
        }
    };

    public ThreadPool(int threadCount) {

        if (threadCount <= 0)
            throw new IllegalArgumentException("Thread count must be > 0");

        threads = Stream.generate(() -> new Thread(work))
                .limit(threadCount)
                .peek(Thread::start)
                .collect(Collectors.toList());

        for (int index = 0; index < threadCount; index++) {
            orderNumber.put((int) threads.get(index).getId(), index);
        }
    }

    public int getOrderNumber(int threadNumber) {
        return orderNumber.get(threadNumber);
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            uncompletedTaskCount++;
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public Integer getTaskCount() {
        return uncompletedTaskCount;
    }

    public void shutdown() {
        for (var tread : threads) {
            try {
                tread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}