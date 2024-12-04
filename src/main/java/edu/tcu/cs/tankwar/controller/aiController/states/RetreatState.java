package edu.tcu.cs.tankwar.controller.aiController.states;

import edu.tcu.cs.tankwar.constants.State;
import edu.tcu.cs.tankwar.controller.GameController;
import edu.tcu.cs.tankwar.controller.aiController.movement.ChaseMovement;
import edu.tcu.cs.tankwar.controller.aiController.movement.RetreatMovement;
import edu.tcu.cs.tankwar.model.EnemyTank;

public class RetreatState extends StateBase{

  public RetreatState(EnemyTank enemyTank) {
    super(enemyTank);
    this.movementBehavior = new RetreatMovement();
  }

  @Override
  public void update(double deltaTime) {
    movementBehavior.move(enemyTank, deltaTime);
  }

  @Override
  public State checkTransition() {
    double distanceToPlayer = enemyTank.getPosition().distance(
            GameController.getInstance().getPlayerTank().getPosition()
    );

    if (!shouldRetreat(enemyTank) && !shouldChase(distanceToPlayer)) {
      return State.CHASE;
    }

    return State.RETREAT;
  }

}
