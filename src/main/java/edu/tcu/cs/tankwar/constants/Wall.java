package edu.tcu.cs.tankwar.constants;

import javafx.scene.paint.Color;

public final class Wall {

  /* Wall settings */
  public static final double WALL_WIDTH = 20;
  public static final double WALL_HEIGHT = 20;
  public static final Color WALL_COLOR = Color.DARKGRAY;

  private Wall() {
    throw new UnsupportedOperationException("Constants class cannot be instantiated");
  }

}
