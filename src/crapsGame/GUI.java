package crapsGame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private Header ProjectHeader;
    private JPanel dicesPanel;
    private GameModel gameManager;
    private JTextArea gameResultPanel;
    private JTextArea MessagesPanel;
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
        gameManager = new GameModel();

        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new GridBagLayout());
        this.add(windowPanel);

        ProjectHeader = new Header("Game Board", Color.BLACK);
        {
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            windowPanel.add(ProjectHeader, c);
        }

        JPanel OptionsPanel = new JPanel();
        {
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            windowPanel.add(OptionsPanel, c);
        }

        JPanel GameStatePanel = new JPanel();
        {
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 2;
            windowPanel.add(GameStatePanel, c);
        }
        dicesPanel = new JPanel();
        dicesPanel.setPreferredSize(new Dimension(305,162));
        dicesPanel.setBorder(BorderFactory.createTitledBorder(
                null,
                "Tus dados",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Calibri", Font.PLAIN, 20),
                Color.BLACK
        ));
        GameStatePanel.add(dicesPanel, BorderLayout.CENTER);
        UpdateDicePanel(0, 0);


        gameResultPanel = new JTextArea(1, 2);
        gameResultPanel.setPreferredSize(new Dimension(305,162));
        gameResultPanel.setBorder(BorderFactory.createTitledBorder(
                null,
                "Resultados",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Calibri", Font.PLAIN, 20),
                Color.BLACK
        ));
        updateResultPanel(0, 0, 0);
        GameStatePanel.add(gameResultPanel, BorderLayout.EAST);


        JButton throwButton = new JButton();
        throwButton.setText("Lanzar Dados");
        throwButton.addActionListener(new ThrowDiceListener());
        {
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 3;
            c.anchor = GridBagConstraints.CENTER;
            windowPanel.add(throwButton, c);
        }

        MessagesPanel = new JTextArea(10, 2);
        MessagesPanel.setBorder(BorderFactory.createTitledBorder(
                null,
                "Mensajes",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Calibri", Font.PLAIN, 20),
                Color.BLACK
        ));
        MessagesPanel.setText(""); //TODO: TUTORIAL
        {
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 4;
            c.fill = GridBagConstraints.BOTH;
            windowPanel.add(MessagesPanel, c);
        }
    }

    public void UpdateDicePanel(int Dice1Number, int Dice2Number)
    {
        dicesPanel.removeAll();
        ImageIcon dice1Image;
        dice1Image = new ImageIcon(getClass().getResource("../resources/" + Dice1Number + ".png"));
        JLabel dice1ImageLabel = new JLabel();
        dice1ImageLabel.setIcon(dice1Image);

        ImageIcon dice2Image;
        dice2Image = new ImageIcon(getClass().getResource("../resources/" + Dice2Number + ".png"));
        JLabel dice2ImageLabel = new JLabel();
        dice2ImageLabel.setIcon(dice2Image);

        dicesPanel.add(dice1ImageLabel, BorderLayout.WEST);
        dicesPanel.add(dice2ImageLabel, BorderLayout.EAST);

        dicesPanel.revalidate();
        dicesPanel.repaint();

    }

    public void updateResultPanel(int firstThrow, int point, int lastThrow)
    {
        String results = "";
        if (firstThrow > 0)
        {
            results += "Tiro de salida: "+ firstThrow+"\n";
        }
        if (point > 0)
        {
            results += "Punto : " + point;
        }
        if (lastThrow > 0)
        {
            results += "Ultimo Tiro: " + lastThrow;
        }
        gameResultPanel.setText(results);
    }
    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class ThrowDiceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gameManager.throwDices();
            int[] diceFaces = gameManager.getDicesFaces();
            UpdateDicePanel(diceFaces[0], diceFaces[1]);

            int[] gameResults = gameManager.getGameResults();
            updateResultPanel(gameResults[0], gameResults[1], gameResults[2]);

            MessagesPanel.setText(gameManager.getStateString());

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
