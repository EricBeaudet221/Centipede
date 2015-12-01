/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import environment.ApplicationStarter;
import environment.Direction;
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
    
    public Centipede(Direction direction, Grid grid, MoveValidatorIntf validator){
        this.direction = direction;
        this.grid = grid;
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
            graphics.fillOval(getGrid().getCellSystemCoordinate(body.get(i)).x,
                    getGrid().getCellSystemCoordinate(body.get(i)).y,
                    getGrid().getCellWidth(),
                    getGrid().getCellHeight());
            
        }
    }
    

    

    private Direction direction = Direction.DOWN;
    private final MoveValidatorIntf validator;
    private final Grid grid;
    private ArrayList<Point> body = new ArrayList<>();
        Color bodyColor;

    private Color getBodyColor() {
        return bodyColor;
    }

    private ArrayList<Point> getBody() {
        return body;
    }

    private Grid getGrid() {
        return grid;
    }
    
}
