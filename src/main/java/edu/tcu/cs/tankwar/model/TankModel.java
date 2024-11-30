package edu.tcu.cs.tankwar.model;

import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.constants.Tank;
import edu.tcu.cs.tankwar.render.TankRender;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class TankModel extends GameObjectModel {
  private final double speed = Tank.TANK_SPEED;
  private double rotation = Tank.TANK_ROTATION;
  private int health = Tank.TANK_MAX_HEALTH;

  public TankModel(double x, double y) {
    super(x, y, Tank.TANK_WIDTH, Tank.TANK_HEIGHT);
  }

  @Override
  public void update(double deltaTime) {
    /* TODO: Handle movement */
  }

  @Override
  public void render(GraphicsContext gc) {
    TankRender.renderTank(this, gc);
  }

  /* Movement methods */
  public void move(double dx, double dy) {
    /* Calculate new position */
    double newX = position.getX() + dx;
    double newY = position.getY() + dy;

    /* Check screen boundaries -> update position if within */
    if (newX >= 0 && newX + width <= Common.WINDOW_WIDTH &&
            newY >= 0 && newY + height <= Common.WINDOW_HEIGHT) {
      position = new Point2D(newX, newY);
    }
  }

  public void rotate(double deg) {
    rotation += deg;
    /* Keep rotation between 0 and 360 degrees */
    rotation = rotation % 360;
    if (rotation < 0) rotation += 360;
  }

  /* Getters and setters */
  public int getHealth() {
    return health;
  }

  public void damage(int dmgAmount) {
    health -= dmgAmount;
    if (health < Tank.TANK_MIN_HEALTH) health = Tank.TANK_MIN_HEALTH;
  }

  public void heal(int healAmount) {
    health += healAmount;
    if (health > Tank.TANK_MAX_HEALTH) health = Tank.TANK_MAX_HEALTH;
  }

  public double getRotation() {
    return rotation;
  }
}
