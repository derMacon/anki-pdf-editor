package com.silas.ankiapiconnector.apiController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Not my work, only modified:
 * https://www.math.uni-hamburg.de/doc/java/tutorial/uiswing/components/example-1dot4/ComboBoxDemo2.java
 */
public class DeckChooser extends JPanel
        implements ActionListener {
    static JFrame frame;
    JLabel result;
    String currentPattern;

    public DeckChooser(String[] decks) {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        String[] patternExamples = decks;

        currentPattern = patternExamples[0];

        //Set up the UI for selecting a pattern.
        JLabel patternLabel1 = new JLabel("Enter the pattern string or");
        JLabel patternLabel2 = new JLabel("select one from the list:");

        JComboBox patternList = new JComboBox(patternExamples);
        patternList.setEditable(true);
        patternList.addActionListener(this);

        //Create the UI for displaying result.
        JLabel resultLabel = new JLabel("Current Date/Time",
                JLabel.LEADING); //== LEFT
        result = new JLabel(" ");
        result.setForeground(Color.black);
        result.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black),
                BorderFactory.createEmptyBorder(5,5,5,5)
        ));

        //Lay out everything.
        JPanel patternPanel = new JPanel();
        patternPanel.setLayout(new BoxLayout(patternPanel,
                BoxLayout.PAGE_AXIS));
        patternPanel.add(patternLabel1);
        patternPanel.add(patternLabel2);
        patternList.setAlignmentX(Component.LEFT_ALIGNMENT);
        patternPanel.add(patternList);

        JPanel resultPanel = new JPanel(new GridLayout(0, 1));
        resultPanel.add(resultLabel);
        resultPanel.add(result);

        patternPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(patternPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(resultPanel);

        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        reformat();
    } //constructor

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String newSelection = (String)cb.getSelectedItem();
        currentPattern = newSelection;
        reformat();
    }

    public void reformat() {
        try {
            String dataString = "";
            result.setForeground(Color.black);
            result.setText(dataString);
        } catch (IllegalArgumentException iae) {
            result.setForeground(Color.red);
            result.setText("Error: " + iae.getMessage());
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI(String[] decks) {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("ComboBoxDemo2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new DeckChooser(decks);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
