package blockingQueue;

public class Worker implements Runnable {

    private final Assignments assignments;

    public Worker(Assignments assignments) {
        this.assignments = assignments;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                render(assignments.getAssignment());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void render(Integer pageNum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " processes page " + pageNum);
    }
}