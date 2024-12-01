package edu.tcu.cs.tankwar.constants;

public final class Tank {

  /* Tank settings */
  public static final double TANK_SPEED = 100;
  public static final int TANK_MAX_HEALTH = 100;
  public static final int TANK_MIN_HEALTH = 100;
  public static final double TANK_ROTATION = 0;
  public static final double TANK_WIDTH = 30;
  public static final double TANK_HEIGHT = 60;
  public static final double CANNON_LENGTH = TANK_HEIGHT * 0.7;
  private Tank() {
    throw new UnsupportedOperationException("Constants class cannot be instantiated");
  }

}
