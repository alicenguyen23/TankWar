package edu.tcu.cs.tankwar.render;
import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.constants.Tank;
import edu.tcu.cs.tankwar.model.TankModel;
import javafx.scene.canvas.GraphicsContext;

import static edu.tcu.cs.tankwar.utils.PixelUtil.snapToPixel;

public class TankRender {

  public static void renderTank(TankModel tankModel, GraphicsContext gc) {
    /* Save current state */
    gc.save();

    /* Move to tank's position and rotation */
    gc.translate(tankModel.getPosition().getX() + tankModel.getWidth() / 2,
            tankModel.getPosition().getY() + tankModel.getHeight() / 2);
    gc.rotate(tankModel.getRotation());

    /* Implement Tank UI */
    double pixelSize = Common.PIXEL_SIZE;
    gc.setImageSmoothing(false);

    /* 1. Tracks */
    double trackWidth = snapToPixel(Tank.TRACK_WIDTH, pixelSize);
    double trackHeight = snapToPixel(tankModel.getHeight() + Tank.TRACK_OFFSET, pixelSize);
    gc.setFill(Tank.GRAY);
    /* Tracks with segments */
    for (int i = 0; i < Tank.TRACK_SEGMENTS; i++) {
      double segmentHeight = trackHeight / Tank.TRACK_SEGMENTS;
      double yOffset = -trackHeight / 2 + i * segmentHeight;
      gc.fillRect(-tankModel.getWidth() / 2 - trackWidth, yOffset, trackWidth, segmentHeight - 2); // Left track
      gc.fillRect(tankModel.getWidth() / 2, yOffset, trackWidth, segmentHeight - 2); // Right track
    }

    /* 2. Body */
    double bodyWidth = snapToPixel(tankModel.getWidth(), pixelSize);
    double bodyHeight = snapToPixel(tankModel.getHeight(), pixelSize);
    gc.setFill(Tank.LIGHT_GREEN);
    gc.fillRect(-bodyWidth / 2, -bodyHeight / 2, bodyWidth, bodyHeight);

    /* 3. Body shading */
    gc.setFill(Tank.DARK_GREEN1);
    gc.fillRect(-bodyWidth / 4, -bodyHeight / 2, bodyWidth / 2, bodyHeight);

    /* 4. Cannon */
    double cannonWidth = snapToPixel(Tank.CANNON_WIDTH, pixelSize);
    double cannonHeight = snapToPixel(tankModel.getHeight() / 2, pixelSize);
    gc.setFill(Tank.DARK_GREEN2);
    gc.fillRect(-cannonWidth / 2, -bodyHeight, cannonWidth, cannonHeight);

    /* 5. Turret base */
    double turretWidth = snapToPixel(Tank.TURRET_WIDTH, pixelSize);
    double turretHeight = snapToPixel(Tank.TURRET_HEIGHT, pixelSize);
    gc.setFill(Tank.DARK_GREEN2);
    gc.fillRect(-turretWidth / 2, -bodyHeight, turretWidth, turretHeight);

    /* Restore state */
    gc.restore();
  }
}
