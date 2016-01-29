/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import environment.Actor;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author ericbeaudet
 */
public class Bullet extends Actor {

    public Bullet(Point position, Velocity velocity) {
        super(position, velocity);
        this.setImage((BufferedImage) ResourceTools.loadImageFromResource("centipede/pew.png"));
    }

}
