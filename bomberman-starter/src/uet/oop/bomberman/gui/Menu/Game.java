package uet.oop.bomberman.gui.Menu;

import uet.oop.bomberman.gui.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Game extends JMenu  {
    public Frame frame;

    public Game(Frame frame) {
        super("Game");
        this.frame = frame;

        /*
         * New Game
         */
        JMenuItem newgame = new JMenuItem("New Game");
       // newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
       newgame.addActionListener(new newGame(frame));
        add(newgame);

    }

}
class newGame implements ActionListener{
    public Frame _frame;
    public newGame (Frame frame) {
        _frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("New Game")) {
            _frame.newGame();
        }
    }

}
