package edu.tcu.cs.tankwar.render;

import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.constants.Wall;
import edu.tcu.cs.tankwar.model.WallModel;
import javafx.scene.canvas.GraphicsContext;

import static edu.tcu.cs.tankwar.utils.PixelUtil.snapToPixel;

public class WallRender {

  public static void renderWall(WallModel wall, GraphicsContext gc) {
    /* Save current state */
    gc.save();

    /* Implement Wall UI */
    gc.drawImage(wall.getWallImage(),
            wall.getPosition().getX(),
            wall.getPosition().getY(),
            wall.getWidth(),
            wall.getHeight());

    /* Restore state */
    gc.restore();
  }

}
