package edu.tcu.cs.tankwar.model;

import edu.tcu.cs.tankwar.constants.Tank;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthBarModel {
  private TankModel tank;
  private double x, y, width, height;

  public HealthBarModel(TankModel tank) {
    this.tank = tank;
    updatePosition();
  }

  private void updatePosition() {
    this.x = tank.getPosition().getX();
    this.y = tank.getPosition().getY() - 20;
    this.width = tank.getWidth();
    this.height = 10;
  }

  public void render(GraphicsContext gc) {
    updatePosition();
    double healthPercentage = (double) tank.getHealth() / Tank.TANK_MAX_HEALTH;
    double healthBarWidth = width * healthPercentage;
    gc.setFill(Color.GREEN);
    gc.fillRect(x, y, healthBarWidth, height);
    gc.setStroke(Color.BLACK);
    gc.strokeRect(x, y, width, height);
  }
}
