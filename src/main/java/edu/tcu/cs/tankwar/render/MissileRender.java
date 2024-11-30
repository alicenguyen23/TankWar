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
    gc.rotate(missile.getRotation());

    /* Implement Missile UI */
    gc.setImageSmoothing(false);
    /* 1. Main body */
    double bodyWidth = snapToPixel(missile.getWidth(), Common.PIXEL_SIZE);
    double bodyHeight = snapToPixel(missile.getHeight() * 0.7, Common.PIXEL_SIZE);
    gc.setFill(Color.GRAY);
    gc.fillRect(-bodyWidth/2, 0, bodyWidth, bodyHeight);

    /* 2. Triangular nose cone */
    gc.setFill(Color.RED);
    double[] xPoints = {
            snapToPixel(-missile.getWidth()/2, Common.PIXEL_SIZE),
            snapToPixel(missile.getWidth()/2, Common.PIXEL_SIZE),
            0
    };
    double[] yPoints = {
            snapToPixel(0, Common.PIXEL_SIZE),
            snapToPixel(0, Common.PIXEL_SIZE),
            snapToPixel(-missile.getHeight() * 0.3, Common.PIXEL_SIZE)
    };
    gc.fillPolygon(xPoints, yPoints, 3);

    /* 3. Fins (darker gray) */
    double finWidth = snapToPixel(missile.getWidth() * 0.5, Common.PIXEL_SIZE);
    double finHeight = snapToPixel(missile.getHeight() * 0.3, Common.PIXEL_SIZE);
    gc.setFill(Color.DARKGRAY);
    /* Left fin */
    double[] leftFinXPoints = {
            snapToPixel(-bodyWidth/2, Common.PIXEL_SIZE), // Base of the triangle (left edge of the missile body)
            snapToPixel(-bodyWidth/2 - finWidth, Common.PIXEL_SIZE), // Left tip of the fin
            snapToPixel(-bodyWidth/2, Common.PIXEL_SIZE) // Base again
    };
    double[] leftFinYPoints = {
            snapToPixel(bodyHeight, Common.PIXEL_SIZE), // Bottom of the missile body
            snapToPixel(bodyHeight + finHeight, Common.PIXEL_SIZE), // Top tip of the fin
            snapToPixel(bodyHeight - finHeight, Common.PIXEL_SIZE) // Top tip again
    };
    gc.fillPolygon(leftFinXPoints, leftFinYPoints, 3);

    /* Right fin */
    double[] rightFinXPoints = {
            snapToPixel(bodyWidth/2, Common.PIXEL_SIZE), // Base of the triangle (right edge of the missile body)
            snapToPixel(bodyWidth/2 + finWidth, Common.PIXEL_SIZE), // Right tip of the fin
            snapToPixel(bodyWidth/2, Common.PIXEL_SIZE) // Base again
    };
    double[] rightFinYPoints = {
            snapToPixel(bodyHeight, Common.PIXEL_SIZE), // Bottom of the missile body
            snapToPixel(bodyHeight + finHeight, Common.PIXEL_SIZE), // Top tip of the fin
            snapToPixel(bodyHeight - finHeight, Common.PIXEL_SIZE) // Top tip again
    };
    gc.fillPolygon(rightFinXPoints, rightFinYPoints, 3);

    /* 4. Flame effect */
    double flameWidth = snapToPixel(missile.getWidth(), Common.PIXEL_SIZE);
    double flameHeight = snapToPixel(missile.getHeight() * 0.4, Common.PIXEL_SIZE);

    /* Outer flame */
    gc.setFill(Color.ORANGE);
    double[] flameXPoints = {
            snapToPixel(-flameWidth/2, Common.PIXEL_SIZE),
            snapToPixel(flameWidth/2, Common.PIXEL_SIZE),
            0
    };
    double[] flameYPoints = {
            snapToPixel(bodyHeight, Common.PIXEL_SIZE),
            snapToPixel(bodyHeight, Common.PIXEL_SIZE),
            snapToPixel(bodyHeight + flameHeight, Common.PIXEL_SIZE)
    };
    gc.fillPolygon(flameXPoints, flameYPoints, 3);

    /* Inner flame */
    gc.setFill(Color.YELLOW);
    double[] innerFlameXPoints = {
            snapToPixel(-flameWidth/3, Common.PIXEL_SIZE),
            snapToPixel(flameWidth/3, Common.PIXEL_SIZE),
            0
    };
    double[] innerFlameYPoints = {
            snapToPixel(bodyHeight, Common.PIXEL_SIZE),
            snapToPixel(bodyHeight, Common.PIXEL_SIZE),
            snapToPixel(bodyHeight + flameHeight * 0.7, Common.PIXEL_SIZE)
    };
    gc.fillPolygon(innerFlameXPoints, innerFlameYPoints, 3);

    /* Restore state */
    gc.restore();
  }
}
