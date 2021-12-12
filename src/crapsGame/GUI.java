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

    private JPanel dicesPanel;
    private GameModel gameManager;
    private JTextArea gameResultPanel;
    private JTextArea MessagesPanel;

    private final String TUTORIAL_TEXT = "El juego comienza con un lanzamiento denominado “tiro de " +
            "salida”. Este tiro corresponde al primer lanzamiento de los\n" +
            "dados que realiza el jugador dando inicio a la ronda de juego.\n" +
            "De acuerdo con el resultado del tiro de salida se procede de la " +
            "siguiente manera:\n" +
            "- Si el lanzamiento da como resultado 2, 3 o 12, esto se " +
            "conoce como “Craps” y significa que el jugador pierde.\n" +
            "-  Si el lanzamiento da como resultado un 7 o un 11, eso es " +
            "conocido como un “Natural” y el jugador gana.\n" +
            "-  Si el lanzamiento da como resultado 4, 5, 6, 8, 9 o 10, eso se " +
            "conoce como punto y su valor será el valor obtenido en el\n" +
            "lanzamiento. En este caso, el jugador podrá seguir " +
            "lanzando los dados con el fin de obtener nuevamente el\n" +
            "valor establecido como punto. Si se logra obtener el valor " +
            "del punto antes de sacar un 7, entonces el jugador gana la\n" +
            "ronda, en caso contrario el jugador pierde la ronda.";
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

        Header projectHeader = new Header("Game Board", Color.BLACK);
        {
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            windowPanel.add(projectHeader, c);
        }

        JPanel helpPanel = new JPanel();
        JButton helpButton = new JButton();
        helpButton.setText("?");
        helpButton.addActionListener(new helpButtonListener());
        JButton exitButton = new JButton();
        exitButton.setText("Exit");
        exitButton.addActionListener(new exitButtonListener());
        helpPanel.add(helpButton, BorderLayout.WEST);
        helpPanel.add(exitButton, BorderLayout.EAST);
        {
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            windowPanel.add(helpPanel, c);
        }

        JPanel gameStatePanel = new JPanel();
        gameStatePanel.setPreferredSize(new Dimension(625,172));
        {
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 2;
            c.fill = GridBagConstraints.BOTH;
            windowPanel.add(gameStatePanel, c);
        }
        dicesPanel = new JPanel();
        dicesPanel.setPreferredSize(new Dimension(302,162));
        dicesPanel.setBorder(BorderFactory.createTitledBorder(
                null,
                "Tus dados",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Calibri", Font.PLAIN, 20),
                Color.BLACK
        ));
        gameStatePanel.add(dicesPanel, BorderLayout.CENTER);
        UpdateDicePanel(0, 0);


        gameResultPanel = new JTextArea(10, 20);
        gameResultPanel.setFocusable(false);
        gameResultPanel.setPreferredSize(new Dimension(250,162));
        gameResultPanel.setBorder(BorderFactory.createTitledBorder(
                null,
                "Resultados",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Calibri", Font.PLAIN, 20),
                Color.BLACK
        ));
        updateResultPanel(0, 0, 0);
        gameStatePanel.add(gameResultPanel, BorderLayout.EAST);


        JButton throwButton = new JButton();
        throwButton.setText("Lanzar Dados");
        throwButton.addActionListener(new ThrowDiceListener());
        {
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 3;
            c.anchor = GridBagConstraints.CENTER;
            windowPanel.add(throwButton, c);
        }

        MessagesPanel = new JTextArea(1, 2);
        MessagesPanel.setFocusable(false);
        MessagesPanel.setBorder(BorderFactory.createTitledBorder(
                null,
                "Mensajes",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Calibri", Font.PLAIN, 20),
                Color.BLACK
        ));
        MessagesPanel.setText(TUTORIAL_TEXT);
        MessagesPanel.setFont(new Font("Calibri", Font.PLAIN, 15));
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
        dice1Image = new ImageIcon(getClass().getResource("/resources/" + Dice1Number + ".png"));
        JLabel dice1ImageLabel = new JLabel();
        dice1ImageLabel.setIcon(dice1Image);

        ImageIcon dice2Image;
        dice2Image = new ImageIcon(getClass().getResource("/resources/" + Dice2Number + ".png"));
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
            results += "Tiro de salida: "+ firstThrow+" \n";
        }
        if (point > 0)
        {
            results += "Punto: " + point+" \n";
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
            MessagesPanel.setFont(new Font("Calibri", Font.PLAIN, 20));

        }
    }

    private class helpButtonListener implements  ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            MessagesPanel.setText(TUTORIAL_TEXT);
            MessagesPanel.setFont(new Font("Calibri", Font.PLAIN, 15));
        }
    }

    private class exitButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            Runtime.getRuntime().exit(0);
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
