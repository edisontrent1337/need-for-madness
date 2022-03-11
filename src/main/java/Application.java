package main.java;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static main.java.Config.*;

public class Application extends JFrame implements MouseListener, KeyListener {

    private int mouseX;
    private int mouseY;
    private Thread game;
    private Graphics graphics;

    public Application() {
        setUp();
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.repaint();
    }

    private void setUp() {
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setTitle("Neeed For Madness");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addMouseListener(this);
        addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
