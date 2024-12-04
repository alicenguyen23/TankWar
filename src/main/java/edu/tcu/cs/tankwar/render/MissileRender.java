package edu.tcu.cs.tankwar.render;

import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.model.MissileModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static edu.tcu.cs.tankwar.utils.PixelUtil.snapToPixel;

public class MissileRender {

  public static void renderMissile(MissileModel missile, GraphicsContext gc) {
    /* Save current state */
    gc.save();
    gc.translate(missile.getPosition().getX() + missile.getWidth()/2, missile.getPosition().getY() + missile.getHeight()/2);

    double imageRotation = missile.getRotation();
    if (!missile.isPlayerMissile()) {
      imageRotation += 180;
    }
    gc.rotate(imageRotation);

    /* Implement Missile UI */
    gc.drawImage(MissileModel.getMissileImage(), -missile.getWidth()/2, -missile.getHeight()/2, missile.getWidth(), missile.getHeight());

    /* Restore state */
    gc.restore();
  }

}
