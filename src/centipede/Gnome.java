/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author ericbeaudet
 */
public class Gnome{
    public Gnome(Direction direction, CellDataProviderIntf cellData, MoveValidatorIntf validator) {
        this.direction = direction;
        this.cellData = cellData;
        this.validator = validator;
    }
    
    public void draw(Graphics graphics) {
        graphics.drawImage(wizard_1,cellData.getSystemCoordX,
                        cellData.getSystemCoordY,
                        cellData.getCellWidth(),
                        cellData.getCellHeight() null);
    }
    
    Image image;
    Direction direction;
    MoveValidatorIntf validator;
    
    Image wizard_1;
    CellDataProviderIntf cellData;
    
}
