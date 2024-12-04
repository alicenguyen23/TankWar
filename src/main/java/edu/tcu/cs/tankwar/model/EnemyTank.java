package edu.tcu.cs.tankwar.model;

import edu.tcu.cs.tankwar.constants.State;
import edu.tcu.cs.tankwar.controller.aiController.states.*;

public class EnemyTank extends TankModel{
  /* AI behaviour related fields */
  private StateBase currentState;
  private State stateType;

  public EnemyTank(double x, double y) {
    super(x, y, false);
    setState(State.CHASE);
  }

  @Override
  public void update(double deltaTime) {
    /* Check for any state transition */
    State newState = currentState.checkTransition();
    if (newState != stateType) {
      setState(newState);
    }

    /* Update current state */
    currentState.update(deltaTime);

    /* Handle base tank updates */
    super.update(deltaTime);
  }

  private void setState(State newState) {
    this.stateType = newState;
    switch (newState) {
      case CHASE -> currentState = new ChaseState(this);
      case ATTACK -> currentState = new AttackState(this);
      case RETREAT -> currentState = new RetreatState(this);
    }
  }

}
