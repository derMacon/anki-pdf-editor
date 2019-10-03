package blockingQueue;

public class Worker implements Runnable {

    private final Assignments assignments;

    public Worker(Assignments assignments) {
        this.assignments = assignments;
    }

    @Override
    public void run() {
        boolean b = true;
        while (b) {
            Integer assignment = assignments.getAssignment();
            b = assignment != null;
            render(assignment);
        }
    }

    private void render(Integer pageNum) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " processes page " + pageNum);
    }
}
