package edu.tcu.cs.tankwar.utils;

import edu.tcu.cs.tankwar.controller.GameController;
import edu.tcu.cs.tankwar.model.GameObjectModel;
import edu.tcu.cs.tankwar.model.WallModel;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.util.List;

public class CollisionUtil {

  public static Rectangle2D getObjectBounds(GameObjectModel object, Point2D position) {
      /* Regular boundary for other objects */
      return new Rectangle2D(
              position.getX(),
              position.getY(),
              object.getWidth(),
              object.getHeight()
      );
  }

  public static boolean checkWallCollision(GameObjectModel object, Point2D newPosition) {
    Rectangle2D objectBoundary = getObjectBounds(object, newPosition);

    List<WallModel> walls = GameController.getInstance().getWalls();

    /* Create boundary for walls and check for collision */
    for (WallModel wall: walls) {
      Rectangle2D wallBoundary = new Rectangle2D(
              wall.getPosition().getX(),
              wall.getPosition().getY(),
              wall.getWidth(),
              wall.getHeight()
      );

      if (objectBoundary.intersects(wallBoundary)) {
        return true;
      }
    }
    return false;
  }

}
