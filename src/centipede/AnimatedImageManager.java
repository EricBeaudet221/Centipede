/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centipede;

import images.ImageManager;
import images.ResourceTools;
import java.util.ArrayList;

/**
 *
 * @author ericbeaudet
 */
public class AnimatedImageManager extends ImageManager {

    public static final String RUN_LEFT_01 = "RUN_LEFT_01";
    public static final String RUN_LEFT_02 = "RUN_LEFT_02";

    public static final String RUN_RIGHT_01 = "RUN_RIGHT_01";
    public static final String RUN_RIGHT_02 = "RUN_RIGHT_02";

    public static ArrayList<String> RUN_LEFT_IMAGE_NAMES;
    public static ArrayList<String> RUN_RIGHT_IMAGE_NAMES;

    public AnimatedImageManager() {
        RUN_LEFT_IMAGE_NAMES = new ArrayList<>();
        RUN_LEFT_IMAGE_NAMES.add(RUN_LEFT_01);
        RUN_LEFT_IMAGE_NAMES.add(RUN_LEFT_02);

        RUN_RIGHT_IMAGE_NAMES = new ArrayList<>();
        RUN_RIGHT_IMAGE_NAMES.add(RUN_RIGHT_01);
        RUN_RIGHT_IMAGE_NAMES.add(RUN_RIGHT_02);
        
        //add my images
        addImage(RUN_LEFT_01, ResourceTools.loadImageFromResource("centipede/body_1.png"));
        addImage(RUN_LEFT_02, ResourceTools.loadImageFromResource("centipede/body_2.png"));
        addImage(RUN_RIGHT_01, ResourceTools.loadImageFromResource("centipede/body_1.png"));
        addImage(RUN_RIGHT_02, ResourceTools.loadImageFromResource("centipede/body_2.png"));

    }

}
