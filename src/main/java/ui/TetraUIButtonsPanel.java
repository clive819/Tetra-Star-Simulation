package main.java.ui;

import main.java.SimulationUIMediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetraUIButtonsPanel extends JPanel {
    protected JButton startButton;
    protected JButton stopButton;
    protected JButton nextStepButton;
    protected SimulationUIMediator mediator;

    public TetraUIButtonsPanel() {
        // set the layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1; // set default weights
        c.weighty = 1;
        c.insets = new Insets(5, 5, 5, 5);

        // create a button and add to the content pane
        startButton = new JButton("Start");
//            button1.addActionListener( new AccountManager() ); //later: action listener
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        startButton.addActionListener(new ButtonListener("start"));
        this.add(startButton, c);


        // repeat for other buttons
        stopButton = new JButton("Stop");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 2;
        stopButton.addActionListener(new ButtonListener("stop"));
        this.add(stopButton, c);

        nextStepButton = new JButton("Next Step");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 3;
        nextStepButton.addActionListener(new ButtonListener("nextStep"));
        this.add(nextStepButton, c);

        setVisible(true);
    }

    public void setMediator(SimulationUIMediator mediator) {
        if (this.mediator == null) {
            this.mediator = mediator;
            mediator.setButtonsPanel(this);
        }
    }

    private class ButtonListener implements ActionListener {
        String buttonAction;

        private ButtonListener() {
        }

        public ButtonListener(String action) {
            buttonAction = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (buttonAction) {
                case "start":
                    mediator.start();
                    break;
                case "stop":
                    mediator.stop();
                    break;
                case "nextStep":
                    mediator.nextStep();
                    break;
                default:
                    System.out.println("INVALID BUTTON ACTION?!");
                    break;
            }
        }
    }
}
