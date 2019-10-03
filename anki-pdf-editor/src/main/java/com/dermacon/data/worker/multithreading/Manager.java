package com.dermacon.data.worker.multithreading;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Manager implements Renderer {

    private static final int THREAD_CNT = 5;

    private Assignments assignments;
    private List<Thread> workers = new LinkedList<>();
    private boolean isRunning = false;

    public Manager(String pdfPath) {
        PDDocument pdf = PDDocument.load(new File(pdfPath));
        assignments = new Assignments(pdf.getNumberOfPages());

        Thread thread;
        for (int i = 0; i < THREAD_CNT; i++) {
            thread = new Thread(new Worker(assignments, pdf));
            thread.start();
            workers.add(thread);
        }

    }

    @Override
    public void renderPageSurrounding(int pageNum) {
        assignments.addPage(pageNum);
    }

    @Override
    public void stop() {
        for(Thread worker : workers) {
            worker.interrupt();
        }
    }
}
