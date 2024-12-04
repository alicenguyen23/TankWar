package edu.tcu.cs.tankwar.controller.aiController.movement;

import edu.tcu.cs.tankwar.controller.GameController;
import edu.tcu.cs.tankwar.model.EnemyTank;
import javafx.geometry.Point2D;

public abstract class MovementBehavior {
  protected void moveTowardsTarget(EnemyTank tank, Point2D target, double deltaTime) {
    Point2D tankCenter = new Point2D(
            tank.getPosition().getX() + tank.getWidth()/2,
            tank.getPosition().getY() + tank.getHeight()/2
    );

    // Calculate normalized direction
    double dx = target.getX() - tankCenter.getX();
    double dy = target.getY() - tankCenter.getY();

    // Move in the calculated direction
    String direction;
    if (Math.abs(dx) > Math.abs(dy)) {
      direction = dx > 0 ? "right" : "left";
    } else {
      direction = dy > 0 ? "down" : "up";
    }

    tank.move(deltaTime, direction);
  }

  protected void faceTarget(EnemyTank tank, Point2D target) {
    // Get centers of both tank and target
    Point2D tankCenter = new Point2D(
            tank.getPosition().getX() + tank.getWidth()/2,
            tank.getPosition().getY() + tank.getHeight()/2
    );

    // Calculate direction vector
    double dx = target.getX() - tankCenter.getX();
    double dy = target.getY() - tankCenter.getY();

    // Calculate angle - 90 degrees to adjust for sprite orientation
    double angle = Math.toDegrees(Math.atan2(dy, dx)) - 90;

    // Normalize angle to 0-360
    while (angle < 0) angle += 360;
    while (angle >= 360) angle -= 360;

    tank.setRotation(angle);
  }

  public abstract void move(EnemyTank enemyTank, double deltaTime);

}
