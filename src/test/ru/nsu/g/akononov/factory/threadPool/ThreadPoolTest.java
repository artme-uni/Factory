package ru.nsu.g.akononov.factory.threadPool;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.nsu.g.akononov.factory.components.car.*;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void execute() {
        ThreadPool threadPool = new ThreadPool(5);

        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                Car car = new Car(new Body(), new Engine(), new Accessory());
            });
        }

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Car:6(Body:6,Engine:6,Accessory:6)", new Car(new Body(), new Engine(), new Accessory()).toString());
    }

    @BeforeClass
    public static void afterClass() {
        Detail.serialNumbers.put("Body", 0);
        Detail.serialNumbers.put("Engine", 0);
        Detail.serialNumbers.put("Accessory", 0);
        Detail.serialNumbers.put("Car", 0);
    }
}