package crapsGame;

import java.util.Random;

public class Dice {
    private int currentFace;
    private Random RNG;

    public Dice()
    {
        RNG = new Random();

    }

    public int throwDice()
    {
        currentFace = RNG.nextInt(6);
        return currentFace;
    }

    public int getCurrentFace()
    {
        return currentFace;
    }
}
