package com.dermacon.ui;


import com.dermacon.data.project.ProjectController;

import java.io.IOException;

public interface UserInterface {
    void openEditor() throws IOException;
    void openPdfViewer() throws IOException;
    void updateProjectInfo();
    void save() throws IOException;
    ProjectController displayProjectInfo();
}
