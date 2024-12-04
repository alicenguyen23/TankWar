package edu.tcu.cs.tankwar.constants;

public final class Common {
  /* Program information */
  public static final String TITLE = "Tank War Game";

  /* Window dimensions */
  public static final double WINDOW_WIDTH = 800.0;
  public static final double WINDOW_HEIGHT = 600.0;
  public static final double WINDOW_BOTTOM_OFFSET = Tank.TANK_HEIGHT + Wall.WALL_HEIGHT;

  /* State related constants */
  public static final double CHASE_RANGE = 400;
  public static final double ATTACK_RANGE = 200;
  public static final double LOW_HEALTH = 20;

  private Common() {
    throw new UnsupportedOperationException("Constants class cannot be instantiated");
  }

}
