package edu.tcu.cs.tankwar.model;

import edu.tcu.cs.tankwar.constants.Explosion;
import edu.tcu.cs.tankwar.render.ExplosionRender;
import edu.tcu.cs.tankwar.utils.ImageUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ExplosionModel extends GameObjectModel{
  private static final Image explosionImage = ImageUtil.EXPLOSION;
  private double animationTime = 0;
  private static final double EXPLOSION_DURATION = Explosion.EXPLOSION_DURATION;

  public ExplosionModel(double x, double y) {
    super(x, y, Explosion.EXPLOSION_WIDTH, Explosion.EXPLOSION_HEIGHT);
  }

  @Override
  public void update(double deltaTime) {
    animationTime += deltaTime;
    if (animationTime > EXPLOSION_DURATION) {
      setActive(false);
    }
  }

  @Override
  public void render(GraphicsContext gc) {
    ExplosionRender.renderExplosion(this, gc);
  }

  public Image getExplosionImage() {
    return explosionImage;
  }
}
