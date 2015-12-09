/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import audio.AudioPlayer;
import environment.Environment;
import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author ericbeaudet
 */
class Garden extends Environment implements CellDataProviderIntf, MoveValidatorIntf {

    private Grid grid;
    private ArrayList<Barrier> barriers;
    private Centipede centipede;
    private Barrier barrier;
    private Graphics graphics;

    Image mushroom_1;

    public Garden() {
        grid = new Grid(40, 30, 17, 17, new Point(10, 50), Color.blue);

        setUpMushrooms(22);

        centipede = new Centipede(Direction.LEFT, this, this);
    }

    public void setUpMushrooms(int number) {
        if (barriers == null) {
            barriers = new ArrayList<>();

        }

        //clean out all the old mushrooms
        barriers.clear();

        // and the number of new mushrooms
        for (int i = 0; i < number; i++) {
            barriers.add(new Barrier(grid.getRandomGridLocation(), Color.ORANGE, this, false));
            if (barriers != null) {
            }

        }

    }

    public void draw(Graphics graphics) {

        if (barriers != null) {
            graphics.drawImage(mushroom_1, grid.getCellHeight(), grid.getCellWidth(), this);
        }
    }

    @Override
    public void initializeEnvironment() {
    }
    int moveDelay = 0;
    int moveDelayLimit = 1;

    @Override
    public void timerTaskHandler() {
        if (centipede != null) {
            if (moveDelay >= moveDelayLimit) {
                centipede.move();
                moveDelay = 0;

            } else {
                moveDelay++;
            }
        }

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
        }else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            AudioPlayer.play("/centipede/laser_1.wav");

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

            if (centipede != null) {
                centipede.draw(graphics);

            }
        }
//          

//            barriers.draw(graphics);
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
        // if off the grid to the left, then
        //  - move down 1
        //  - move to the right one (back onto the grid)
        //  - change direction to RIGHT
        if (proposedLocation.x < 0) {
            proposedLocation.y++;
            proposedLocation.x++;
            centipede.setDirection(Direction.RIGHT);
        }

        // if off the grid to the right, then
        //  - move down 1
        //  - move to the left one (back onto the grid)
        //  - change direction to LEFT
        //
        if (proposedLocation.x >= grid.getColumns()) {
            proposedLocation.y++;
            proposedLocation.x--;
            centipede.setDirection(Direction.LEFT);
        }

        //if the head hits a mushroom from the right move it down and change direction to left.
        //if the head hits a mushroom from the left move it down and change direction to right.
        if (barriers != null) {
            for (Barrier barrier : barriers) {
                if (barrier.getLocation().equals(proposedLocation)) {
                    //move locatation down
                    proposedLocation.y++;

//                    change direction
                    if (centipede.getDirection() == Direction.RIGHT) {
                        centipede.setDirection(Direction.LEFT);
                    } else if (centipede.getDirection() == Direction.LEFT) {
                        centipede.setDirection(Direction.RIGHT);
                    }

                }
            }
        }

//        if (proposedLocation.x < 0) {
//            System.out.println("OUT OF BOUNDS");
//        }
//        return null;
        return proposedLocation;
    }

//</editor-fold>
}
