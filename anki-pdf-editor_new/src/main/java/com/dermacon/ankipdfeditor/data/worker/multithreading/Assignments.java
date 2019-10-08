package com.dermacon.ankipdfeditor.data.worker.multithreading;

import java.util.LinkedList;

class Assignments {

    private static final int PAGE_INTERVALL = 2; //todo
    private LinkedList<Integer> assignments = new LinkedList<>();
    private LinkedList<Integer> renderedImages = new LinkedList<>();
    private int pageCnt;

    public Assignments(int pageCnt) {
        this.pageCnt = pageCnt;
    }

    public synchronized void addPage(int pageNum) {
        if (isValidPage(pageNum)) {
            assignments.add(0, pageNum);

            int next, prev;
            for (int i = 1; i < PAGE_INTERVALL; i++) {
                next = pageNum + i;
                prev = pageNum - i;

                if (isValidPage(prev)) {
                    assignments.add(i, prev);
                }

                if (isValidPage(next)) {
                    assignments.add(i, next);
                }

            }
        }

        this.notifyAll();
    }

    private boolean isValidPage(int pageNum) {
        return pageNum > 0 && pageNum <= pageCnt;
    }

    public synchronized Integer getAssignment() {

        while (assignments.isEmpty() && !Thread.currentThread().isInterrupted()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Integer assignment = null;
        if (!Thread.currentThread().isInterrupted()) {
            assignment = assignments.remove(0);

            // assignment list may contain duplicates due to
            // the priority of the 'younger' assignments.
            // easier to just filter out already rendered img
            // instead of being picky when creating assignments.
            if (renderedImages.contains(assignment)) {
                return getAssignment();
            }

            this.renderedImages.add(assignment);
        }
        return assignment;
    }

    @Override
    public String toString() {
        return assignments.toString();
    }
}
