/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import environment.ApplicationStarter;

/**
 *
 * @author ericbeaudet
 */
public class Centipede {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationStarter.run("Centipede Squash", new Garden());
        
    }
    
}
