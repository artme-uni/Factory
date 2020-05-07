
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MyProducer {
    private final int capacity = 10;
    private final LinkedList<Integer> storage;

    MyProducer(LinkedList<Integer> storage)
    {
        this.storage = storage;
    }

    public void produce() throws InterruptedException
    {
        int value = 0;

        synchronized (storage) {
            while (storage.size() == capacity)
                storage.wait();

            storage.add(value++);
            System.out.println("Produce" + value + "\t\t" + storage.size() + "\\" + capacity);

            storage.notify();

            Thread.sleep(1000);
        }
    }
}