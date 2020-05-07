package ru.nsu.g.akononov.factory.car;

import java.util.HashMap;
import java.util.Map;

abstract public class Detail {
    static final Map<String, Integer> serialNumbers = new HashMap<>();
    int serialNumber;

    public Detail() {
        synchronized (serialNumbers) {
            String name = getClass().getSimpleName();

            serialNumbers.putIfAbsent(name, 0);
            serialNumbers.put(name, serialNumbers.get(name) + 1);
            serialNumber = serialNumbers.get(name);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + serialNumber;
    }
}

