package edu.tcu.cs.tankwar.controller.aiController.movement;

import edu.tcu.cs.tankwar.controller.GameController;
import edu.tcu.cs.tankwar.model.EnemyTank;
import javafx.geometry.Point2D;

public class ChaseMovement extends MovementBehavior {

  @Override
  public void move(EnemyTank enemyTank, double deltaTime) {
    Point2D playerPos = GameController.getInstance().getPlayerTank().getPosition();
    moveTowardsTarget(enemyTank, playerPos, deltaTime);
  }

}
