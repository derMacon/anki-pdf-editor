package com.dermacon.ankipdfeditor.ui;



import com.dermacon.ankipdfeditor.data.project.ProjectController;
import com.dermacon.ankipdfeditor.export.Formating;

import java.io.IOException;

/**
 * Terminal ui that processes the queries given by the terminalLauncher.
 */
public interface UserInterface {

    /**
     * Opens the editor to create new cards
     * @throws IOException
     */
    void openEditor() throws IOException;

    /**
     * Opens a panel to open the selected pdf in
     * @throws IOException
     */
    void openPdfViewer() throws IOException;

    /**
     * Opens a dialog to change either the
     * - deck
     * - pdf
     * - export directory
     * @throws IOException
     */
    void updateProjectInfo() throws IOException;

    /**
     * Pushes the content from the currently selected .anki file
     * @throws IOException
     */
    void pushToAnki() throws IOException;

    /**
     * Saves the projectInfo component to a specified file
     * @throws IOException
     */
    void saveProjHistory() throws IOException;

    /**
     * Getter for the ProjectController
     * @return ProjectController component
     */
    ProjectController getProjectController();

    /**
     * Opens a dialog from which a user can select which card stack should be
     * exported in which format
     * @throws IOException
     */
    void exportAnyStack() throws IOException;

    /**
     * Exports the currently selected stack to the specified export directory
     * in the html format.
     * @throws IOException
     */
    void exportCurrStack() throws IOException;
}
