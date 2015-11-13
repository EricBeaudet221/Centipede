/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import environment.Environment;
import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author ericbeaudet
 */
class Garden extends Environment {

    Grid grid;

    public Garden() {
        grid = new Grid(75, 60, 17, 17, new Point(10, 50), Color.blue);
    }

    @Override
    public void initializeEnvironment() {
    }

    int counter;

    @Override
    public void timerTaskHandler() {
//        System.out.println("hi" + ++counter);
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Go left");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Go right");

        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Go up");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Go down");
        }

    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (e.getKeyCode() ==KeyEvent.VK_W) {
            System.out.println("up");
        }
         if (e.getKeyCode() ==KeyEvent.VK_A) {
            System.out.println("left");
        }
          if (e.getKeyCode() ==KeyEvent.VK_S) {
            System.out.println("down");
        }
         if (e.getKeyCode() ==KeyEvent.VK_D) {
            System.out.println("right");
        }
    
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("Mouse click at" + e.getPoint());
        System.out.println("Mouse clicked in cell " + grid.getCellLocationFromSystemCoordinate(e.getPoint()));

    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);
        }

    }

}
