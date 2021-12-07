package crapsGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private Header headerProject;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        this.setTitle("Craps Game");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {

        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        //Set up JComponents

        this.setLayout (new GridLayout (3,1));
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonName = ((MyButton) e.getSource()).getButtonName();

            switch(buttonName){
                case "ThrowDicesButton":
                {
                    //TODO: throw dice
                }break;
                default :
                {
                    //TODO: print out ERROR button doesn't have a name
                }break;
            }
        }
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }
}
