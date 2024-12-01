package edu.tcu.cs.tankwar.render;
import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.constants.Tank;
import edu.tcu.cs.tankwar.model.TankModel;
import javafx.scene.canvas.GraphicsContext;

import static edu.tcu.cs.tankwar.utils.PixelUtil.snapToPixel;

public class TankRender {

  public static void renderTank(TankModel tank, GraphicsContext gc) {
    /* Save current state */
    gc.save();

    /* Move to tank's position and rotation */
    gc.translate(tank.getPosition().getX() + tank.getWidth() / 2,
            tank.getPosition().getY() + tank.getHeight() / 2);
    gc.rotate(tank.getRotation());

    /* Implement Tank UI */
    gc.drawImage(tank.getTankImage(),
            -tank.getWidth()/2,
            -tank.getHeight()/2,
            tank.getWidth(),
            tank.getHeight());

    /* Restore state */
    gc.restore();
  }

}
