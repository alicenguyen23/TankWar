package edu.tcu.cs.tankwar.constants;

import javafx.scene.paint.Color;

public final class Tank {
  /* Tank settings */
  public static final double TANK_SPEED = 200.0;
  public static final int TANK_MAX_HEALTH = 100;
  public static final int TANK_MIN_HEALTH = 100;
  public static final double TANK_ROTATION = 0;
  public static final double TANK_WIDTH = 40.0;
  public static final double TANK_HEIGHT = 60.0;

  /* Tank colors */
  public static final Color LIGHT_GREEN = Color.web("#4CAF50");
  public static final Color DARK_GREEN1 = Color.web("#388E3C");
  public static final Color DARK_GREEN2 = Color.web("#2E7D32");
  public static final Color GRAY =  Color.GRAY;

  /* Track */
  public static final double TRACK_WIDTH = 10.0;
  public static final double TRACK_OFFSET = 20.0;
  public static final int TRACK_SEGMENTS = 5;

  /* Cannon */
  public static final double CANNON_WIDTH = 10;

  /* Turret */
  public static final double TURRET_WIDTH = 20;
  public static final double TURRET_HEIGHT = 10;

  private Tank() {
    throw new UnsupportedOperationException("Constants class cannot be instantiated");
  }

}