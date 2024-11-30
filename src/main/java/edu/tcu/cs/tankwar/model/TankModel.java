package edu.tcu.cs.tankwar.model;

import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.constants.Missile;
import edu.tcu.cs.tankwar.constants.Tank;
import edu.tcu.cs.tankwar.render.TankRender;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class TankModel extends GameObjectModel {
  private final double speed = Tank.TANK_SPEED;
  private double rotation = Tank.TANK_ROTATION;
  private int health = Tank.TANK_MAX_HEALTH;
  private List<MissileModel> missiles = new ArrayList<>();
  private double lastShotTime = 0;

  public TankModel(double x, double y) {
    super(x, y, Tank.TANK_WIDTH, Tank.TANK_HEIGHT);
  }

  @Override
  public void update(double deltaTime) {
    /* TODO: Handle movement */
    /* Update missiles movement */
    missiles.removeIf(missile -> !missile.isActive());
    missiles.forEach(missile -> missile.update(deltaTime));
  }

  @Override
  public void render(GraphicsContext gc) {
    /* Render tank */
    TankRender.renderTank(this, gc);
    /* Render missiles */
    missiles.forEach(missile -> missile.render(gc));
  }

  /* Movement methods */
  public void move(double deltaTime, String direction) {
    double dx = 0, dy = 0;
    /* Calculate displacement based on the direction */
    switch (direction.toLowerCase()) {
      case "up":
        dy = -Tank.TANK_SPEED * deltaTime;
        break;
      case "down":
        dy = Tank.TANK_SPEED * deltaTime;
        break;
      case "left":
        dx = -Tank.TANK_SPEED * deltaTime;
        break;
      case "right":
        dx = Tank.TANK_SPEED * deltaTime;
        break;
    }

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

  /* Shooting method */
  public void shoot(double currentTime) {
    /* If no longer in cooldown time */
    if (currentTime - lastShotTime > Missile.SHOT_COOLDOWN) {
      /* Calculate turret position (where the cannon ends) */
      double turretEndY = position.getY() - height;
      /* Spawn missiles at turret end */
      double xPosition = position.getX() + width/2 - Missile.MISSILE_WIDTH/2;
      double yPosition = turretEndY - Missile.MISSILE_HEIGHT/2;

      /* Generate a missile */
      MissileModel missile = new MissileModel(xPosition, yPosition, rotation);
      missiles.add(missile);
      lastShotTime = currentTime;
    }
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
