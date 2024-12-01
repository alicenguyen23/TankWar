package edu.tcu.cs.tankwar.model;

import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.constants.Missile;
import edu.tcu.cs.tankwar.render.MissileRender;
import edu.tcu.cs.tankwar.utils.CollisionUtil;
import edu.tcu.cs.tankwar.utils.ImageUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static edu.tcu.cs.tankwar.utils.PixelUtil.snapToPixel;

public class MissileModel extends GameObjectModel {

  private static final Image missileImage = ImageUtil.MISSILE;
  private final double rotation;
  private final double speed = Missile.MISSILE_SPEED;
  private boolean active = true;

  public MissileModel(double x, double y, double rotation) {
    super(x,y, Missile.MISSILE_WIDTH, Missile.MISSILE_HEIGHT);
    this.rotation = rotation;
  }

  @Override
  public void update(double deltaTime) {
    if (!active) return;

    /* Missile movement based on rotation */
    double adjustedRotation = rotation - Missile.ROTATION_OFFSET;
    double rad = Math.toRadians(adjustedRotation);
    double dx = Math.cos(rad) * speed * deltaTime;
    double dy = Math.sin(rad) * speed * deltaTime;

    /* Calculate new position */
    double newX = position.getX() + dx;
    double newY = position.getY() + dy;
    Point2D newPosition = new Point2D(newX, newY);

    // Check wall collisions using GameState
    if (checkWallCollision(newPosition)) {
      active = false;  /* Missile is absorbed by wall */
      return;
    }

    /* Check screen boundaries */
    if (newX >= 0 && newX + width <= Common.WINDOW_WIDTH &&
            newY >= 0 && newY + height <= Common.WINDOW_HEIGHT) {
      position = newPosition;
    } else {
      active = false;
    }
  }

  @Override
  public void render(GraphicsContext gc) {
    if (!active) return;
    MissileRender.renderMissile(this, gc);
  }

  /* Check for wall collision */
  private boolean checkWallCollision(Point2D missilePosition) {
    return CollisionUtil.checkWallCollision(this, missilePosition);
  }

  /* Getters ans setters */
  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public void setActive(boolean active) {
    this.active = active;
  }

  public double getRotation() {
    return rotation;
  }

  public static Image getMissileImage() {
    return missileImage;
  }

}
