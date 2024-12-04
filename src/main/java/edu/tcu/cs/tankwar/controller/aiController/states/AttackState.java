package edu.tcu.cs.tankwar.controller.aiController.states;

import edu.tcu.cs.tankwar.constants.State;
import edu.tcu.cs.tankwar.controller.GameController;
import edu.tcu.cs.tankwar.controller.aiController.movement.AttackMovement;
import edu.tcu.cs.tankwar.model.EnemyTank;

public class AttackState extends StateBase{

  public AttackState(EnemyTank enemyTank) {
    super(enemyTank);
    this.movementBehavior = new AttackMovement();
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
    if (!shouldAttack(distanceToPlayer)) {
      return State.CHASE;
    }

    if (shouldRetreat(enemyTank)) {
      return State.RETREAT;
    }

    return State.ATTACK;
  }

}
