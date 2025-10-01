package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class ProducerConsumer {
    private final Semaphore semaphore = new Semaphore(0);
    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
    private final Object lock = new Object();

    public void produce() {
        synchronized (lock) {
            int num = 10;
            queue.add(num);
            System.out.println("added value = " + num + " to queue");
        }
        semaphore.release();
    }

    public void consume() throws InterruptedException {
        semaphore.acquire();
        synchronized (lock) {
            System.out.println("removed " + queue.poll() + " value from queue");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();
        Thread producer = new Thread(() -> {
            try {
                pc.produce();
            } catch(Exception ex) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
