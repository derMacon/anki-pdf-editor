package com.dermacon.ankipdfeditor.ui;



import com.dermacon.ankipdfeditor.data.project.ProjectController;
import com.dermacon.ankipdfeditor.export.Formating;

import java.io.IOException;

public interface UserInterface {
    void openEditor() throws IOException;
    void openPdfViewer() throws IOException;
    void updateProjectInfo() throws IOException;
    void pushToAnki() throws IOException;
    void saveProjHistory() throws IOException;
    ProjectController getProjectController();
    void exportAnyStack() throws IOException;
    void exportCurrStack() throws IOException;
}
