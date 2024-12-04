package edu.tcu.cs.tankwar.utils;

import edu.tcu.cs.tankwar.controller.GameController;
import edu.tcu.cs.tankwar.model.*;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.util.List;

public class CollisionUtil {

  public static Rectangle2D getObjectBoundary(GameObjectModel object, Point2D position) {
      /* Regular boundary for other objects */
      return new Rectangle2D(
              position.getX(),
              position.getY(),
              object.getWidth(),
              object.getHeight()
      );
  }

  /* Check for collision between Wall and other objects */
  public static boolean checkWallCollision(GameObjectModel object, Point2D newPosition) {
    Rectangle2D objectBoundary = getObjectBoundary(object, newPosition);

    List<WallModel> walls = GameController.getInstance().getWalls();

    /* Create boundary for walls and check for collision */
    for (WallModel wall: walls) {
      Rectangle2D wallBoundary = getObjectBoundary(wall, wall.getPosition());

      if (objectBoundary.intersects(wallBoundary)) {
        return true;
      }
    }
    return false;
  }

  /* Check for collision between Missile and Tank */
  public static boolean checkMissileTankCollision(MissileModel missile, TankModel tank) {
    /* Get missile boundary */
    Rectangle2D missileBoundary = getObjectBoundary(missile, missile.getPosition());

    /* Get tank boundary */
    Rectangle2D tankBoundary = getObjectBoundary(tank, tank.getPosition());

    /* Check for collision */
    return missileBoundary.intersects(tankBoundary);
  }

  /* Check for collision between Tanks */
  public static boolean checkTankCollision(TankModel tank1, TankModel tank2) {
    Rectangle2D tankBoundary1 = getObjectBoundary(tank1, tank1.getPosition());
    Rectangle2D tankBoundary2 = getObjectBoundary(tank2, tank2.getPosition());
    return tankBoundary1.intersects(tankBoundary2);
  }

  /* Check for collision between MedPack and Tank*/
  public static boolean checkMedTankCollision(MedPackModel medpack, TankModel tank) {
    /* Get medpack boundary */
    Rectangle2D medpackBoundary = getObjectBoundary(medpack, medpack.getPosition());

    /* Get tank boundary */
    Rectangle2D tankBoundary = getObjectBoundary(tank, tank.getPosition());

    /* Check for collision */
    return medpackBoundary.intersects(tankBoundary);
  }

}
