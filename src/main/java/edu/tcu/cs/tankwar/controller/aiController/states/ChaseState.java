package edu.tcu.cs.tankwar.controller.aiController.states;

import edu.tcu.cs.tankwar.constants.State;
import edu.tcu.cs.tankwar.controller.GameController;
import edu.tcu.cs.tankwar.controller.aiController.movement.ChaseMovement;
import edu.tcu.cs.tankwar.model.EnemyTank;

public class ChaseState extends StateBase{

  public ChaseState(EnemyTank enemyTank) {
    super(enemyTank);
    this.movementBehavior = new ChaseMovement();
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
    if (shouldAttack(distanceToPlayer)) {
      return State.ATTACK;
    }

    if (shouldRetreat(enemyTank)) {
      return State.RETREAT;
    }

    return State.CHASE;
  }

}
