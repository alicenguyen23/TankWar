package edu.tcu.cs.tankwar.model;

import edu.tcu.cs.tankwar.constants.Tank;
import edu.tcu.cs.tankwar.render.TankRender;
import javafx.scene.canvas.GraphicsContext;

public class TankModel extends GameObject {
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
  public void move(double deltaTime) {
    double dx = Math.cos(Math.toRadians(rotation)) * speed * deltaTime;
    double dy = Math.sin(Math.toRadians(rotation)) * speed * deltaTime;
    position = position.add(dx, dy);
  }

  public void rotate(double deg) {
    rotation += deg;
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
