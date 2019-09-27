package com.silas.ankiapiconnector.logic;

public class CardFactory {

    /**
     * Syntax:
     * 1. lines beginning with * => comment
     * 2. front: [text]
     * 3. back: [text]
     * 4. tags: [multiple tags]
     * 5. line beginning with - => delimiter
     * @param editorInput
     * @return
     */
    public static Card[] produceStack(String editorInput) {
        System.out.println("stack: " + editorInput);
        return null;
    }
}
