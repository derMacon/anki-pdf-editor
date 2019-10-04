package com.dermacon.data.worker.multithreading;

import com.dermacon.data.project.ProjectInfo;

import java.util.LinkedList;
import java.util.List;

public class Manager implements Renderer {

    private static final int THREAD_CNT = 2; //todo

    private final Assignments assignments;
    private final List<Thread> workers = new LinkedList<>();
    private final ProjectInfo projectInfo;

    private boolean isRunning = false; //todo ???

    public Manager(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;

        int pageCnt = projectInfo.getPdfPDDoc().getNumberOfPages();
        assignments = new Assignments(pageCnt);

        Thread thread;
        for (int i = 0; i < THREAD_CNT; i++) {
            thread = new Thread(new Worker(assignments, projectInfo));
            thread.start();
            workers.add(thread);
        }

    }

    @Override
    public void renderPageIntervall() {
        assignments.addPage(projectInfo.getCurrPage());
    }

    @Override
    public void stop() {
        for(Thread worker : workers) {
            worker.interrupt();
        }
    }
}
