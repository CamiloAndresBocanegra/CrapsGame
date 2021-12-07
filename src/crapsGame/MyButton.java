package crapsGame;

import java.awt.*;


public class MyButton extends Button {
    private String buttonName;

    public MyButton(String name)
    {
        buttonName = name;
    }

    public String getButtonName()
    {
        return buttonName;
    }
}
