package main.java.logging;

import javax.swing.*;

public class TLogger {

    public static TLogger shared = new TLogger();

    private JTextArea textArea;

    private TLogger() {
    }

    public void log(String message) {
        if (textArea != null) {
            textArea.append(message + "\n");
        } else {
            System.out.println(message);
        }
    }

    public void setOutput(JTextArea textArea) {
        this.textArea = textArea;
    }

}
