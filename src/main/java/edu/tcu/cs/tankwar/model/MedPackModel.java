package edu.tcu.cs.tankwar.model;

import edu.tcu.cs.tankwar.constants.MedPack;
import edu.tcu.cs.tankwar.render.MedPackRender;
import edu.tcu.cs.tankwar.utils.ImageUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MedPackModel extends GameObjectModel {
  private static final Image medPackImage = ImageUtil.MEDPACK;

  public MedPackModel(double x, double y) {
    super(x, y, MedPack.MEDPACK_WIDTH, MedPack.MEDPACK_HEIGHT);
  }

  @Override
  public void update(double deltaTime) {
  }

  @Override
  public void render(GraphicsContext gc) {
    MedPackRender.renderMedPack(this, gc);
  }

  public Image getMedPackImage() {
    return medPackImage;
  }

}