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
import java.util.ArrayList;

/**
 *
 * @author ericbeaudet
 */
class Garden extends Environment implements CellDataProviderIntf, MoveValidatorIntf {

    Grid grid;
    private ArrayList<Barrier> barriers;

    public Garden() {
        grid = new Grid(40, 30, 17, 17, new Point(10, 50), Color.blue);

        barriers = new ArrayList<>();
        barriers.add(new Barrier(0, 2, Color.GREEN, this, false));
        barriers.add(new Barrier(0, 3, Color.GREEN, this, false));
        barriers.add(new Barrier(0, 4, Color.ORANGE, this, false));
        barriers.add(new Barrier(0, 5, Color.GREEN, this, false));
        barriers.add(new Barrier(0, 6, Color.CYAN, this, false));

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

        if (barriers != null) {
            for (int i = 0; i < barriers.size(); i++) {
                barriers.get(i).draw(graphics);
                
            }
//            
            
//            barriers.draw(graphics);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="CellDataProviderIntf">
    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }

    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }

    @Override
    public int getSystemCoordX(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).x;

    }

    @Override
    public int getSystemCoordY(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).y;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MoveValidatorIntf">
    @Override
    public Point validateMove(Point proposedLocation) {
        if (proposedLocation.x < 0) {

            if (proposedLocation.y > grid.getRows() / 2) {
                proposedLocation.x++;
                proposedLocation.y++;

            } else {
                proposedLocation.x++;
                proposedLocation.y--;
            }

            System.out.println("OUT OF BOUNDS");

        }

        return proposedLocation;
    }

//</editor-fold>
}
