package edu.tcu.cs.tankwar.render;

import edu.tcu.cs.tankwar.model.ExplosionModel;
import edu.tcu.cs.tankwar.model.MissileModel;
import edu.tcu.cs.tankwar.model.TankModel;
import javafx.scene.canvas.GraphicsContext;

public class ExplosionRender {

  public static void renderExplosion(ExplosionModel explosion, GraphicsContext gc) {
    /* Save current state */
    gc.save();

    /* Implement Explosion UI */
    gc.drawImage(explosion.getExplosionImage(),
            explosion.getPosition().getX(),
            explosion.getPosition().getY(),
            explosion.getWidth(),
            explosion.getHeight());

    /* Restore state */
    gc.restore();
  }

}
