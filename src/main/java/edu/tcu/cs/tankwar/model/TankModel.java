package edu.tcu.cs.tankwar.model;

import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.constants.Missile;
import edu.tcu.cs.tankwar.constants.Tank;
import edu.tcu.cs.tankwar.render.TankRender;
import edu.tcu.cs.tankwar.utils.CollisionUtil;
import edu.tcu.cs.tankwar.utils.ImageUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class TankModel extends GameObjectModel {

  private final Image tankImage;
  private double speed;
  private double rotation = Tank.TANK_ROTATION;
  private int health = Tank.TANK_MAX_HEALTH;
  private List<MissileModel> missiles = new ArrayList<>();
  private boolean isPlayer;
  private double lastShotTime = 0;

  public TankModel(double x, double y, boolean isPlayer) {
    super(x, y, Tank.TANK_WIDTH, Tank.TANK_HEIGHT);
    this.tankImage = isPlayer ? ImageUtil.PLAYER_TANK : ImageUtil.ENEMY_TANK;
    this.speed = isPlayer ? Tank.TANK_SPEED : Tank.ENEMY_SPEED;
    this.isPlayer = isPlayer;
  }

  @Override
  public void update(double deltaTime) {
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
        dy = -speed * deltaTime;
        break;
      case "down":
        dy = speed * deltaTime;
        break;
      case "left":
        dx = -speed * deltaTime;
        break;
      case "right":
        dx = speed * deltaTime;
        break;
    }

    /* Calculate new position */
    double newX = position.getX() + dx;
    double newY = position.getY() + dy;
    Point2D newPosition = new Point2D(newX, newY);

    /* Check for screen and wall boundaries */
    if (!checkWallCollision(newPosition)) {
      if (newX >= 0 && newX + width <= Common.WINDOW_WIDTH &&
              newY >= 0 && newY + height <= Common.WINDOW_HEIGHT) {
        position = newPosition;
      }
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
    double shootingCooldown = isPlayer ? Missile.PLAYER_COOLDOWN : Missile.ENEMY_COOLDOWN;
    if (currentTime - lastShotTime > shootingCooldown) {
      /* Calculate position to spawn missile (taking rotation angle into consideration) */
      double centerX = position.getX() + width/2;
      double centerY = position.getY() + height/2;

      double adjustedRotation = isPlayer ?
              rotation - Missile.ROTATION_OFFSET :
              rotation + Missile.ROTATION_OFFSET;
      double rad = Math.toRadians(adjustedRotation);
      double xPosition = centerX + Math.cos(rad) * Tank.CANNON_LENGTH - Missile.MISSILE_WIDTH/2;
      double yPosition = centerY + Math.sin(rad) * Tank.CANNON_LENGTH - Missile.MISSILE_HEIGHT/2;

      /* Generate a missile */
      MissileModel missile = new MissileModel(xPosition, yPosition, rotation, isPlayer);
      missiles.add(missile);
      lastShotTime = currentTime;
    }
  }

  /* Check for wall collision */
  private boolean checkWallCollision(Point2D tankPosition) {
    return CollisionUtil.checkWallCollision(this, tankPosition);
  }

  /* Getters and setters */
  public Image getTankImage() {
    return tankImage;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void damage(int dmgAmount) {
    health -= dmgAmount;
    if (health <= Tank.TANK_MIN_HEALTH) {
      health = Tank.TANK_MIN_HEALTH;
      isActive = false;
    }
  }

  public double getRotation() {
    return rotation;
  }

  public void setRotation(double rotation) {
    this.rotation = rotation;
  }

  public List<MissileModel> getMissiles() {
    return missiles;
  }

}
