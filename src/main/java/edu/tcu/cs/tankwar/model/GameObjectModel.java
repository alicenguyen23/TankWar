package edu.tcu.cs.tankwar.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObjectModel {
  /* Basic properties */
  protected Point2D position; /* x and y coordinates */
  protected double width; /* object's dimension */
  protected double height; /* object's dimension */
  protected boolean isActive; /* if object is still active */

  public GameObjectModel(double x, double y, double width, double height) {
    this.position = new Point2D(x, y);
    this.width = width;
    this.height = height;
    this.isActive = true;
  }

  /* Update game logic */
  public abstract void update(double deltaTime);
  /* Render game object */
  public abstract void render(GraphicsContext gc);

  /* Collision detection method */
  public boolean collides(GameObjectModel other) {
    return position.getX() < other.position.getX() + other.width &&
            position.getX() + width > other.position.getX() &&
            position.getY() < other.position.getY() + other.height &&
            position.getY() + height > other.position.getY();
  }

  /* Getters and setters */

  public Point2D getPosition() {
    return position;
  }

  public void setPosition(Point2D position) {
    this.position = position;
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }
}
