package main.java.ui;

import main.java.SimulationUIMediator;
import main.java.logging.TLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TetraUI {
    public MainUIPanel mainUIPanel;
    public ActionListener buttonListener;

    public TetraUI() {
        mainUIPanel = new MainUIPanel();
        mainUIPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setMediator(SimulationUIMediator mediator) {
        mainUIPanel.setMediator(mediator);
    }

    class MainUIPanel extends JFrame {
        public TetraUIDrawingPanel drawingPanel;
        public TetraUIButtonsPanel buttonsPanel;
        public JTextArea textPanel;

        public MainUIPanel() {
            Container container = getContentPane();
            container.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            drawingPanel = new TetraUIDrawingPanel();

            // place drawing panel
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(10, 10, 0, 10); //padding: all but bot
            c.weightx = 3;
            c.weighty = 3;
            c.gridx = 0;
            c.gridy = 0;
            container.add(drawingPanel, c);

            // place text panel
            textPanel = new JTextArea("");
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(10, 10, 10, 10); //padding: all
            c.weightx = 3;
            c.weighty = 2;
            c.gridx = 0;
            c.gridy = 1;
            container.add(textPanel, c);

            // place buttons panel
            buttonsPanel = new TetraUIButtonsPanel();
            c.fill = GridBagConstraints.NONE;
            c.weightx = 1;
            c.weighty = 1;
            c.gridx = 1;
            c.gridy = 0;
            c.gridheight = 2;
            container.add(buttonsPanel, c);

            setSize(1200, 600);
            setVisible(true);

            TLogger.shared.setOutput(textPanel);
        }

        private void setMediator(SimulationUIMediator mediator) {
            buttonsPanel.setMediator(mediator);
            drawingPanel.setMediator(mediator);
        }
    }
}
