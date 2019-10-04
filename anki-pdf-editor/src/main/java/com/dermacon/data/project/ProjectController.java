package com.dermacon.data.project;

import com.dermacon.data.worker.multithreading.Manager;
import com.dermacon.data.worker.multithreading.Renderer;

import java.io.IOException;

public class ProjectController {

    private ProjectInfo projectInfo;
    private Renderer renderer;

    public ProjectController() throws IOException {
        this.projectInfo = new FileBuilder().build();
        renderer = new Manager(projectInfo);
        renderer.renderPageIntervall();
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public int turnNextPage() {
        int out = projectInfo.getCurrPage();
        if (out < projectInfo.getPdfDoc().getNumberOfPages()) {
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

    public String getCurrPageImage() {
        return projectInfo.getCurrImgPath();
    }

    public void saveProjHistory() throws IOException {
        projectInfo.saveToFile();
    }
}
