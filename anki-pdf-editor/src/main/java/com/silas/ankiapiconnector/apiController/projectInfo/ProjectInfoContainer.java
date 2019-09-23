package com.silas.ankiapiconnector.apiController.projectInfo;

import java.io.IOException;

public class ProjectInfoContainer {

    private static ProjectInfo singleton = null;

    public static void setProjectInfo(ProjectInfo projectInfo) {
        singleton = projectInfo;
    }

    public static ProjectInfo getProjectInfo() throws IOException {
        if (singleton == null) {
            singleton = new ProjectInfo();
        }
        return singleton;
    }

}
