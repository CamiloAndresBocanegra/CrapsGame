package crapsGame;

import java.lang.reflect.Array;

public class GameModel {
    private Dice dice1, dice2;

    private int lastThrowValue = 0,
            firstThrowValue = 0,
            targetPoint = 0,
            gameState = 0;

    private final int
            NATURAL_WIN = 1,
            CRAPS_LOSE = 2,
            POINT_SET = 3,
            WIN = 4,
            LOSE = 5;

    private boolean isPointSet = false;

    private String StringState;

    public GameModel()
    {
        dice1 = new Dice();
        dice2 = new Dice();
    }

    public int[] getDicesFaces()
    {
        int[] diceValues = new int[2];
        diceValues[0] = dice1.getCurrentFace();
        diceValues[1] = dice2.getCurrentFace();
        return diceValues;
    }

    public void throwDices()
    {
        updateGameState(dice1.throwDice() + dice2.throwDice());
    }

    public void updateGameState(int value)
    {
        if (isPointSet)
        {
            lastThrowValue = value;

            if (lastThrowValue == 7)
            {
                gameState = LOSE;
                isPointSet = false;
            }
            else if(lastThrowValue == targetPoint)
            {
                gameState = WIN;
                isPointSet = false;
            }
        }else{
            firstThrowValue = value;
            lastThrowValue = 0;
            targetPoint = 0;
            switch (value)
            {
                case 7:
                case 11:
                {
                    gameState = NATURAL_WIN;
                }break;

                case 2:
                case 3:
                case 12:
                {
                    gameState = CRAPS_LOSE;
                }break;

                default:
                {
                    gameState = POINT_SET;
                    targetPoint = value;
                    isPointSet = true;
                }
            }
        }
    }
    public int[] getGameResults()
    {
        int[] gameResults = new int[3];
        gameResults[0] = firstThrowValue;
        gameResults[1] = targetPoint;
        gameResults[2] = lastThrowValue;

        return gameResults;

    }

    public String getStateString()
    {
        switch (gameState)
        {
            case NATURAL_WIN:{
                StringState = "Sacaste Natural, has ganado!";
            }break;

            case CRAPS_LOSE:{
                StringState = "Sacaste Craps, has perdido";
            }break;

            case POINT_SET:{
                StringState = "Sacaste "+targetPoint+" como punto \n" +
                        "Si sacas "+targetPoint+" otra vez, ganas! pero si sacas 7 Pierdes!!";
            }break;

            case WIN:{
                StringState = "Volviste a conseguir "+targetPoint+", has Ganado!!";
            }break;

            case LOSE:{
                StringState = "Sacaste 7 antes que"+targetPoint+", Has perdido!!";
            }break;

            default:{
                //TODO: printout ERROR NOT VALID STATE
            }break;
        }
        return StringState;
    }


    public int getTargetPoint()
    {
        return targetPoint;
    }
}
