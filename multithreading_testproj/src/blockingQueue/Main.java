package blockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Assignments assignments = new Assignments(20);

        List<Thread> lst = new LinkedList<>();
        lst.add(createNewThread(assignments));
        lst.add(createNewThread(assignments));
        lst.add(createNewThread(assignments));
        lst.add(createNewThread(assignments));

        assignments.addPage(3);
        assignments.addPage(4);
        assignments.addPage(1);

        Thread.sleep(5000);
        System.out.println("nau");
        assignments.addPage(10);

        Thread.sleep(5000);
        System.out.println("yeehaw");
        assignments.addPage(15);
        assignments.addPage(30);

        for(Thread t : lst) {
            t.interrupt();
        }


    }

    private static Thread createNewThread(Assignments assignments) {
        Thread t = new Thread(new Worker(assignments));
        t.start();
        return t;
    }

}
