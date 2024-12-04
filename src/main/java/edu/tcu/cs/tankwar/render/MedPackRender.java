package edu.tcu.cs.tankwar.render;

import edu.tcu.cs.tankwar.model.MedPackModel;
import javafx.scene.canvas.GraphicsContext;

public class MedPackRender {

  public static void renderMedPack(MedPackModel medpack, GraphicsContext gc) {
    /* Save current state */
    gc.save();

    /* Implement MedPack UI */
    gc.drawImage(medpack.getMedPackImage(),
            medpack.getPosition().getX(),
            medpack.getPosition().getY(),
            medpack.getWidth(),
            medpack.getHeight());

    /* Restore state */
    gc.restore();
  }

}
