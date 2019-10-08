package com.dermacon.ankipdfeditor.data.worker.multithreading;

/**
 * Opens a pseudo priority queue for assignements which consists of
 * page numbers that should be rendered by the worker. The workers
 * are managed by the Renderer itself and rely on a semaphore blocking
 * mechanism of the queue. If there are no more assignments left, instead
 * of stopping these worker threads wait for the next assignments.
 */
public interface Renderer {

    /**
     * Adds the given page number (currpage in projInfo) to the beginning of the
     * queue (highest priority) and adds the surrounding page numbers afterwards.
     * A constant determines how many page numbers should be rendered.
     *
     * Example: surrounding == 2;
     * 1. Add page 5: queue == [5, 6, 4, 7, 3]
     * 2. Add page 8: queue == [8, 9, 7, 10, 6, 5, 6, 4, 7, 3]
     */
    public void renderPageIntervall();

    public void stop();

}
