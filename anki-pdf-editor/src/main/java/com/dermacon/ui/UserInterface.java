package com.dermacon.ui;


import com.dermacon.logic.ProjectInfo;

import java.io.IOException;

public interface UserInterface {
    void openEditor() throws IOException;
    void openPdfViewer() throws IOException;
    void updateProjectInfo();
    void save();
    ProjectInfo displayProjectInfo();
}
