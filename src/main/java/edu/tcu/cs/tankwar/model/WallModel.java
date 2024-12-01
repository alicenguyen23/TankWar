package edu.tcu.cs.tankwar.model;

import edu.tcu.cs.tankwar.constants.Wall;
import edu.tcu.cs.tankwar.render.WallRender;
import edu.tcu.cs.tankwar.utils.ImageUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class WallModel extends GameObjectModel {
  private static final Image wallImage = ImageUtil.WALL;
  public WallModel(double x, double y) {
    super(x, y, Wall.WALL_WIDTH, Wall.WALL_HEIGHT);
  }

  @Override
  public void update(double deltaTime) {
    /* Don't need to update since walls are static */
  }

  @Override
  public void render(GraphicsContext gc) {
    WallRender.renderWall(this, gc);
  }

  public Image getWallImage() {
    return wallImage;
  }

}
