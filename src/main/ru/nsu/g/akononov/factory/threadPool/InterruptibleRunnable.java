package ru.nsu.g.akononov.factory.threadPool;

public interface InterruptibleRunnable {
    void run() throws InterruptedException;
}
