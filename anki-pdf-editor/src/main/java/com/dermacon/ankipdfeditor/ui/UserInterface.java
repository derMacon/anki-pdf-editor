package com.dermacon.ankipdfeditor.ui;

import com.dermacon.ankipdfeditor.apiController.ProjectInfo;

import java.io.IOException;

public interface UserInterface {
    void openEditor() throws IOException;
    void updateProjectInfo();
    void save();
    ProjectInfo displayProjectInfo();
}
