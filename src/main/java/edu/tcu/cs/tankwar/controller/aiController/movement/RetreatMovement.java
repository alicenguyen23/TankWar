package edu.tcu.cs.tankwar.controller.aiController.movement;

import edu.tcu.cs.tankwar.controller.GameController;
import edu.tcu.cs.tankwar.model.EnemyTank;
import javafx.geometry.Point2D;

public class RetreatMovement extends MovementBehavior {
  @Override
  public void move(EnemyTank enemyTank, double deltaTime) {
    Point2D playerPos = GameController.getInstance().getPlayerTank().getPosition();
    Point2D enemyPos = enemyTank.getPosition();

    double dx = enemyPos.getX() - playerPos.getX();
    double dy = enemyPos.getY() - playerPos.getY();

    Point2D retreatPoint = new Point2D(
            enemyPos.getX() + dx,
            enemyPos.getY() + dy
    );

    moveTowardsTarget(enemyTank, retreatPoint, deltaTime);
  }

}
