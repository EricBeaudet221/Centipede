/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import environment.ApplicationStarter;
import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author ericbeaudet
 */
public class Centipede {
    

    public void move() {
        //create a new head
        Point newHead = new Point(getHead());
        
        if (direction == Direction.LEFT) {
            newHead.x--;
        } else if (direction == Direction.RIGHT) {
            newHead.x++;
        }

        //validate that it is ok to move
        newHead = validator.validateMove(newHead);
        
        // put it ahead of the old head, depending on direction
        body.add(HEAD_POSITION, newHead);
        
        //delete the tail
        body.remove(body.size()-1);
    }

    public Centipede(Direction direction, CellDataProviderIntf cellData, MoveValidatorIntf validator) {
        this.direction = direction;
        this.cellData = cellData;
        this.validator = validator;

        //body
        body = new ArrayList<>();
        body.add(new Point(10, 17));
        body.add(new Point(10, 18));
        body.add(new Point(10, 19));
        body.add(new Point(10, 20));
    }

    public void draw(Graphics graphics) {
        graphics.setColor(getBodyColor());

        for (int i = 0; i < body.size(); i++) {
            graphics.setColor(bodyColor);
            
            graphics.fillOval(cellData.getSystemCoordX(body.get(i).x, body.get(i).y),
                              cellData.getSystemCoordY(body.get(i).x, body.get(i).y),
                              cellData.getCellWidth(),
                              cellData.getCellHeight());

        }
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    private final CellDataProviderIntf cellData;
    private final MoveValidatorIntf validator;
    private ArrayList<Point> body = new ArrayList<>();
    private Color bodyColor;
    private Direction direction;
    
    private final int HEAD_POSITION = 0;
    
    private Color getBodyColor() {
        return bodyColor;
    }
    
    private ArrayList<Point> getBody() {
        return body;
    }
    
    
    private Point getHead() {
        return body.get(HEAD_POSITION);
    }


    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    //</editor-fold>
}
