package com.dermacon.data.worker.multithreading;

import org.apache.pdfbox.pdmodel.PDDocument;

public class Worker implements Runnable {

    private final Assignments assignments;
    private final PDDocument pdf;

    public Worker(Assignments assignments, PDDocument pdf) {
        this.assignments = assignments;
        this.pdf = pdf;
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
        System.out.println(Thread.currentThread().getName() + " processes page " + pageNum + " from " + pdf.toString());
        // todo
    }
}
