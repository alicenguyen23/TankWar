package edu.tcu.cs.tankwar.controller.aiController.states;

import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.constants.State;
import edu.tcu.cs.tankwar.controller.aiController.movement.MovementBehavior;
import edu.tcu.cs.tankwar.model.EnemyTank;

public abstract class StateBase {
  protected EnemyTank enemyTank;
  protected MovementBehavior movementBehavior;
  protected static final double CHASE_RANGE = Common.CHASE_RANGE;
  protected static final double ATTACK_RANGE = Common.ATTACK_RANGE;
  protected static final double LOW_HEALTH = Common.LOW_HEALTH;

  public StateBase(EnemyTank enemyTank) {
    this.enemyTank = enemyTank;
  }

  /* Update current state */
  public abstract void update(double deltaTime);

  /* Check for state transition */
  public abstract State checkTransition();

  /*State checking methods */
  public boolean shouldChase(double distanceToPlayer) {
    return distanceToPlayer < CHASE_RANGE;
  }

  public boolean shouldAttack(double distanceToPlayer) {
    boolean shouldAttack = distanceToPlayer < ATTACK_RANGE;
    return distanceToPlayer < ATTACK_RANGE;
  }

  public boolean shouldRetreat(EnemyTank tank) {
    return tank.getHealth() <= LOW_HEALTH;
  }

}
