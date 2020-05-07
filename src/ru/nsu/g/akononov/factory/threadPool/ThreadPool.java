package ru.nsu.g.akononov.factory.threadPool;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadPool {

    private final LinkedList<Runnable> taskQueue = new LinkedList<>();
    private final List<Thread> threads;

    Runnable work = () -> {
        while (true) {
            synchronized (taskQueue) {
                while (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Runnable task = taskQueue.poll();
                task.run();
            }
        }
    };

    public ThreadPool(int threadCount) {

        if (threadCount <= 0)
            throw new IllegalArgumentException("Thread count must be > 0");

        threads = Stream.generate(() -> new Thread(work))
                .limit(threadCount)
                .peek(Thread::start)
                .collect(Collectors.toList());
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void shutdown()
    {
        for(var tread : threads)
        {
            try {
                tread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
