package com.dermacon.ui;


import com.dermacon.data.project.ProjectController;

import java.io.IOException;

public interface UserInterface {
    void openEditor() throws IOException;
    void openPdfViewer() throws IOException;
    void updateProjectInfo() throws IOException;
    void push() throws IOException;
    void save() throws IOException;
    ProjectController getProjectController();
}