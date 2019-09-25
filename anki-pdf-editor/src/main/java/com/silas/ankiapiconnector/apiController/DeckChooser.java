package com.silas.ankiapiconnector.apiController;

import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;
import javax.swing.*;

/**
 * Not my work, only slightly modified:
 * https://www.math.uni-hamburg.de/doc/java/tutorial/uiswing/components/example-1dot4/ComboBoxDemo2.java
 */
public class DeckChooser extends JPanel
        implements ActionListener {
    static JFrame frame;
    JLabel result;
    String currentPattern;

    private Consumer<String> callback;
    private JButton btn;
    private TextField textfld = new TextField("");

    public DeckChooser(String[] decks, Consumer<String> callback) {

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
        JLabel resultLabel = new JLabel("Anki deck selector",
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

        btn = new JButton("submit");
        btn.addActionListener(e -> {
            String textInput = textfld.getText();
            if (textInput.length() == 0) {
                textInput = currentPattern;
            }
            callback.accept(textInput);
            frame.dispose();
        });

        add(patternPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(textfld);
        add(btn);

        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        reformat();
    } //constructor

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String newSelection = (String)cb.getSelectedItem();
    }

    // todo delete
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
    public static void createAndShowGUI(String[] decks, Consumer<String> callback) {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        frame = new JFrame("DeckSeletor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new DeckChooser(decks, callback);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
