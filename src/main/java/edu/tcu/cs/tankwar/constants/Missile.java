package edu.tcu.cs.tankwar.constants;

public final class Missile {

  /* Missile settings */
  public static final double MISSILE_SPEED = 150;
  public static final double MISSILE_WIDTH = 15;
  public static final double MISSILE_HEIGHT = 30;
  public static final double ROTATION_OFFSET = 90;
  public static final int MISSILE_DAMAGE = 20;
  public static final double PLAYER_COOLDOWN = 0.5;
  public static final double ENEMY_COOLDOWN = 1;

  private Missile() {
    throw new UnsupportedOperationException("Constants class cannot be instantiated");
  }

}
