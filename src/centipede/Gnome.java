/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author ericbeaudet
 */
public class Gnome {

    private int x;
    private int y;

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }

    public Gnome(Image image, int x, int y, Direction direction, CellDataProviderIntf cellData, MoveValidatorIntf validator) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.direction = direction;
        this.cellData = cellData;
        this.validator = validator;
    }

    private Image image;
    Direction direction;
    MoveValidatorIntf validator;

    CellDataProviderIntf cellData;

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

}
