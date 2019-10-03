package blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Assignments assignments = new Assignments(20);
        new Thread(new Worker(assignments)).start();
        new Thread(new Worker(assignments)).start();
        new Thread(new Worker(assignments)).start();
        new Thread(new Worker(assignments)).start();

        assignments.addPage(3);
        assignments.addPage(4);
        assignments.addPage(1);

        Thread.sleep(5000);
        System.out.println("nau");
        assignments.addPage(10);
    }

}
