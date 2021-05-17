package ui;

import javax.swing.*;
import java.awt.*;

class TetraUIButtonsPanel extends JPanel{
    protected JButton startButton;
    protected JButton stopButton;
    protected JButton nextStepButton;

    public TetraUIButtonsPanel(){
        // set the layout
        this.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1; // set default weights
        c.weighty = 1;
        c.insets = new Insets(5,5,5,5);

        // create a button and add to the content pane
        startButton = new JButton( "Start");
//            button1.addActionListener( new AccountManager() ); //later: action listener
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        this.add(startButton, c);


        // repeat for other buttons
        stopButton = new JButton( "Stop");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 2;
        this.add(stopButton, c);

        nextStepButton = new JButton( "Next Step");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 3;
        this.add(nextStepButton, c);

        setVisible( true );
    }
}
