/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import audio.AudioPlayer;
import environment.Environment;
import environment.Velocity;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
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
    private ArrayList<Barrier> mushrooms;
    private ArrayList<Bullet> pews;
    private Centipede centipede;
    private Barrier barrier;
    private Graphics graphics;
    Image wizardType;
    Gnome wizard;
    Image mushroom_1;
    int move = 8;
    private int score;

    public Garden() {
        Image wizardType = ResourceTools.loadImageFromResource("Centipede/wizard_1.png");
        this.setBackground(ResourceTools.loadImageFromResource("Centipede/Garden_3.png"));

        wizard = new Gnome(wizardType, 38, 553, Direction.LEFT, this, this);

        grid = new Grid(40, 30, 17, 17, new Point(10, 50), Color.GREEN);

        setUpMushrooms(25);
        pews = new ArrayList<>();

        centipede = new Centipede(Direction.LEFT, this, this);
    }

    public void setUpMushrooms(int number) {
        if (mushrooms == null) {
            mushrooms = new ArrayList<>();

        }

        //clean out all the old mushrooms
        mushrooms.clear();

        // and the number of new mushrooms
        for (int i = 0; i < number; i++) {
            mushrooms.add(new Barrier(grid.getRandomGridLocation(), Color.ORANGE, this, false));
            if (mushrooms != null) {
            }
        }
    }

    public void draw(Graphics graphics) {

        if (mushrooms != null) {
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

        if (pews != null) {
            for (Bullet bullet : pews) {
                bullet.move();
            }
        }
        checkBulletHits();
    }

    private void checkBulletHits() {
        //see if the bullet will intersect with anything.
        //if it hits a mushroom then it disapears.
        //if it hits the centipede then the centipede's size is reduced by one point.

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            wizard.setX(wizard.getX() - move);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            wizard.setX(wizard.getX() + move);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            wizard.setY(wizard.getY() - move);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            wizard.setY(wizard.getY() + move);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shoot();
        } else if (e.getKeyCode() == KeyEvent.VK_Y) {
            AudioPlayer.play("/centipede/Yoda_Song.wav");

        }
    }

    private void shoot() {
        pews.add(new Bullet(new Point(wizard.getX() - 30, wizard.getY()), new Velocity(0, -20)));
        AudioPlayer.play("/centipede/laser_1.wav");
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

        if (mushrooms != null) {
            for (int i = 0; i < mushrooms.size(); i++) {
                mushrooms.get(i).draw(graphics);
            }
        }

        if (pews != null) {
            for (Bullet bullet : pews) {
                bullet.paint(graphics);
            }
        }

        if (centipede != null) {
            centipede.draw(graphics);
        }

        if (wizard != null) {
            wizard.draw(graphics);
        }
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Times", Font.BOLD, 35));
        graphics.drawString("SCORE:" + score++, 20, 30);
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
        if (mushrooms != null) {
            for (Barrier barrier : mushrooms) {
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
