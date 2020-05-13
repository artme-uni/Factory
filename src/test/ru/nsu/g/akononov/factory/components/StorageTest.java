package ru.nsu.g.akononov.factory.components;

import org.junit.Test;

import static org.junit.Assert.*;

public class StorageTest {

    @Test
    public void getBodiesStorage() {
        Storage storage = new Storage(10, 20, 30, 40);
        assertEquals(0, storage.getBodiesStorage().size());
        assertEquals(10, storage.getBodiesStorageCapacity());
    }

    @Test
    public void getEnginesStorage() {
        Storage storage = new Storage(10, 20, 30, 40);
        assertEquals(0, storage.getEnginesStorage().size());
        assertEquals(20, storage.getEnginesStorageCapacity());
    }

    @Test
    public void getAccessoriesStorage() {
        Storage storage = new Storage(10, 20, 30, 40);
        assertEquals(0, storage.getAccessoriesStorage().size());
        assertEquals(30, storage.getAccessoriesStorageCapacity());
    }

    @Test
    public void getCarsStorage() {
        Storage storage = new Storage(10, 20, 30, 40);
        assertEquals(0, storage.getCarsStorage().size());
        assertEquals(40, storage.getCarsStorageCapacity());
    }
}