package uet.oop.bomberman.gui.Menu;

import uet.oop.bomberman.gui.Frame;

import javax.swing.*;

public class menu extends JMenuBar {
    public menu(Frame frame){
        add(new Game(frame));
    }
}
