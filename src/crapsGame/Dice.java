package crapsGame;

import java.util.Random;

public class Dice {
    int currentFace;

    public int throwDice()
    {
        Random RNG = new Random();
        currentFace = RNG.nextInt(6);
        return currentFace;
    }

    public int getCurrentFace()
    {
        return currentFace;
    }
}
