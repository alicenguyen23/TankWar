package edu.tcu.cs.tankwar.controller.aiController.movement;

import edu.tcu.cs.tankwar.controller.GameController;
import edu.tcu.cs.tankwar.model.EnemyTank;
import edu.tcu.cs.tankwar.utils.TimeUtil;
import javafx.geometry.Point2D;

public class AttackMovement extends MovementBehavior {

  @Override
  public void move(EnemyTank enemyTank, double deltaTime) {
    Point2D playerPos = GameController.getInstance().getPlayerTank().getPosition();

    // Face player regardless of movement
    faceTarget(enemyTank, playerPos);

    // Shoot when in attack mode
    enemyTank.shoot(TimeUtil.nanoToSeconds(System.nanoTime()));
  }

}