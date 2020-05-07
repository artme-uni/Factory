import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyConsumer {
    private final int consume = 10;
    private final LinkedList<Integer> storage;

    MyConsumer(LinkedList<Integer> storage)
    {
        this.storage = storage;
    }

    public void consume() throws InterruptedException
    {
        synchronized (storage)
            {
                System.out.println("N");
                while (storage.size() == 0)
                    storage.wait();

                int val = storage.removeFirst();

                System.out.println("Consume" + val);
                storage.notify();

                Thread.sleep(1000);
            }
        }
    }

