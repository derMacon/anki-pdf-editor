package com.dermacon.ankipdfeditor.data.project;

import com.dermacon.ankipdfeditor.data.worker.multithreading.Manager;
import com.dermacon.ankipdfeditor.data.worker.multithreading.Renderer;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;

public class ProjectController {

    private ProjectInfo projectInfo;
    private Renderer renderer;

    public ProjectController() throws IOException {
        this.projectInfo = new InfoBuilder().parseHistoryFile().build();
        renderer = new Manager(projectInfo);
        renderer.renderPageIntervall();
    }

    public void setPdf(File pdf) throws IOException {
        projectInfo = new InfoBuilder()
                .copy(projectInfo)
                .setPdf(pdf)
                .build();
    }

    public void setDeck(String deck) throws IOException {
        projectInfo = new InfoBuilder()
                .copy(projectInfo)
                .setDeck(deck)
                .build();
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public int turnNextPage() {
        int out = projectInfo.getCurrPage();
        if (out < projectInfo.getPdfPDDoc().getNumberOfPages()) {
            projectInfo.setCurrPage(++out);
            renderer.renderPageIntervall();
        }
        return out;
    }

    public int turnPrevPage() {
        int out = projectInfo.getCurrPage();
        if (out > 0) {
            projectInfo.setCurrPage(--out);
            renderer.renderPageIntervall();
        }
        return out;
    }

    public Image getCurrPageImage() {
        return projectInfo.getCurrImg();
    }

    public void saveProjHistory() throws IOException {
        projectInfo.saveToFile();
    }
}
