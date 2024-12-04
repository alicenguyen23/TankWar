package edu.tcu.cs.tankwar.utils;

import javafx.scene.image.Image;

import java.util.Objects;

public class ImageUtil {

    public static final Image PLAYER_TANK = new Image(Objects.requireNonNull(ImageUtil.class.getResourceAsStream("/edu/tcu/cs/tankwar/images/tank/player-tank.png")));
    public static final Image ENEMY_TANK = new Image(Objects.requireNonNull(ImageUtil.class.getResourceAsStream("/edu/tcu/cs/tankwar/images/tank/enemy-tank.png")));
    public static final Image MISSILE = new Image(Objects.requireNonNull(ImageUtil.class.getResourceAsStream("/edu/tcu/cs/tankwar/images/missile/missile.png")));
    public static final Image WALL = new Image(Objects.requireNonNull(ImageUtil.class.getResourceAsStream("/edu/tcu/cs/tankwar/images/wall/wall.png")));
    public static final Image EXPLOSION = new Image(Objects.requireNonNull(ImageUtil.class.getResourceAsStream("/edu/tcu/cs/tankwar/images/explosion/explosion.png")));
    public static final Image MEDPACK = new Image(Objects.requireNonNull(ImageUtil.class.getResourceAsStream("/edu/tcu/cs/tankwar/images/medpack/medkit.png")));

}
