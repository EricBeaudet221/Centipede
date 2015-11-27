/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import java.awt.Point;

/**
 *
 * @author ericbeaudet
 */
public interface MoveValidatorIntf {
    public Point validateMove(Point proposedLocation);
    
}
