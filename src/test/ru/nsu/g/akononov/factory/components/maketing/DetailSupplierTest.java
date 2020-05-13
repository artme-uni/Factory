package ru.nsu.g.akononov.factory.components.maketing;

import org.junit.Test;
import ru.nsu.g.akononov.factory.components.Storage;
import ru.nsu.g.akononov.factory.components.car.Engine;

import java.util.LinkedList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class DetailSupplierTest {

    @Test
    public void supplyEngine() {
        LinkedList<Engine> engineStorage = new LinkedList<>();
        DetailSupplier<Engine> engineSupplier = new DetailSupplier<>(10, engineStorage, Engine::new, 0);
        engineSupplier.start();
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(10, engineStorage.size());

        engineSupplier.stop();
    }
}