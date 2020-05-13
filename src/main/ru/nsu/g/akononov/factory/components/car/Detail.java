package ru.nsu.g.akononov.factory.components.car;

import ru.nsu.g.akononov.factory.components.observable.operation;

import java.util.HashMap;
import java.util.Map;

abstract public class Detail {
    public static final Map<String, Integer> serialNumbers = new HashMap<>();
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

    public operation getOperation(){
        switch (getClass().getSimpleName())
        {
            case "Body" : return operation.bodiesCount;
            case "Accessory" : return operation.accessoriesCount;
            case "Engine" : return operation.enginesCount;
        }

        return null;
    }
}

