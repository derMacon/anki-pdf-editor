package com.dermacon.ankipdfeditor.ui;

import com.dermacon.ankipdfeditor.apiController.ProjectInfo;
import com.dermacon.ankipdfeditor.logic.Card;

public interface BackendLink {
    void addCards(Card[] cards);
    void updateProjectInfo(ProjectInfo projectInfo);
}
