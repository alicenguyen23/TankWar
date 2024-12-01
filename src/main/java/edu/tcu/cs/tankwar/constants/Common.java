package edu.tcu.cs.tankwar.constants;

public final class Common {
  /* Program information */
  public static final String TITLE = "Tank War Game";

  /* Window dimensions */
  public static final double WINDOW_WIDTH = 800.0;
  public static final double WINDOW_HEIGHT = 600.0;
  public static final double WINDOW_BOTTOM_OFFSET = Tank.TANK_HEIGHT + Wall.WALL_HEIGHT;

  /* Pixel */
  public static final double PIXEL_SIZE = 5;

  private Common() {
    throw new UnsupportedOperationException("Constants class cannot be instantiated");
  }

}
