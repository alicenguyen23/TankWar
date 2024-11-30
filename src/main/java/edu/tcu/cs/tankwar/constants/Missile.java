package edu.tcu.cs.tankwar.constants;

public final class Missile {
  /* Missile settings */
  public static final double MISSILE_SPEED = 150;
  public static final double MISSILE_WIDTH = 10;
  public static final double MISSILE_HEIGHT = 20;
  public static final double ROTATION_OFFSET = 90;
  public static final int MISSILE_DAMAGE = 20;
  public static final double SHOT_COOLDOWN = 0.5;

  private Missile() {
    throw new UnsupportedOperationException("Constants class cannot be instantiated");
  }

}
