package com.dermacon.ankipdfeditor.data.project;

import com.dermacon.ankipdfeditor.data.worker.multithreading.Manager;
import com.dermacon.ankipdfeditor.data.worker.multithreading.Renderer;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;

/**
 * Controller that wraps the projectInfo component with the renderer that
 * coordinates the worker threads in the background.
 */
public class ProjectController {

    private ProjectInfo projectInfo;
    private Renderer renderer;

    /**
     * Constructor
     * @throws IOException thrown if the InfoBuilder component cannot be
     * created.
     */
    public ProjectController() throws IOException {
        this.projectInfo = new InfoBuilder().parseHistoryFile().build();
        renderer = new Manager(projectInfo);
        renderer.renderPageIntervall();
    }

    // todo just use projectInfo.setPdf(pdf) ...
    public void setPdf(File pdf) throws IOException {
        projectInfo = new InfoBuilder()
                .copy(projectInfo)
                .setPdf(pdf)
                .build();
    }

    // todo same as setPdf
    public void setDeck(String deck) throws IOException {
        projectInfo = new InfoBuilder()
                .copy(projectInfo)
                .setDeck(deck)
                .build();
    }

    /**
     * Getter for the projectInfo component.
     * @return projectInfo component.
     */
    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    /**
     * Turns the currPage attribute of the projectInfo component.
     * @return updated currpage attribute of the projectInfo component
     */
    public int turnNextPage() {
        int out = projectInfo.getCurrPage();
        if (out < projectInfo.getPdfPDDoc().getNumberOfPages()) {
            projectInfo.setCurrPage(++out);
            renderer.renderPageIntervall();
        }
        return out;
    }

    /**
     * Turns the currPage attribute of the projectInfo component.
     * @return updated currpage attribute of the projectInfo component
     */
    public int turnPrevPage() {
        int out = projectInfo.getCurrPage();
        if (out > 0) {
            projectInfo.setCurrPage(--out);
            renderer.renderPageIntervall();
        }
        return out;
    }

    // todo
    public Image getCurrPageImage() {
        return projectInfo.getCurrImg();
    }

    /**
     * Calls the save option of the projectInfo component.
     * @throws IOException thrown if the file io does not work properly
     */
    public void saveProjHistory() throws IOException {
        projectInfo.saveToFile();
    }
}
