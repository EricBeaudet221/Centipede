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
import java.awt.Point;

/**
 *
 * @author ericbeaudet
 */
public class Barrier {

    public void draw(Graphics graphics) {
        graphics.setColor(color);
//        graphics.fill3DRect(cellData.getSystemCoordX(x, y),
//                cellData.getSystemCoordY(x, y),
//                cellData.getCellHeight(),
//                cellData.getCellWidth(), true);
        graphics.drawImage(image,cellData.getSystemCoordX(x, y),
                cellData.getSystemCoordY(x, y),
                cellData.getCellHeight(),
                cellData.getCellWidth(), null);
    
    }

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        image = ResourceTools.loadImageFromResource("centipede/mushroom_1.png");
    }
    
    public Barrier(int x, int y, Color color, CellDataProviderIntf cellData, boolean breakable) {
        this.x = x;
        this.y = y;

        this.color = color;
        this.cellData = cellData;
    }

    public Barrier(Point location, Color color, CellDataProviderIntf cellData, boolean breakable) {
        this.x = location.x;
        this.y = location.y;

        this.color = color;
        this.cellData = cellData;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="properties">
    private int x, y;
    private Color color;
    private boolean breakable = false;
    private CellDataProviderIntf cellData;
    Image image;
    
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

    public Point getLocation() {
        return new Point(x, y);
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the breakable
     */
    public boolean isBreakable() {
        return breakable;
    }

    /**
     * @param breakable the breakable to set
     */
    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }
//</editor-fold>
}
