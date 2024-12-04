package edu.tcu.cs.tankwar.controller;

import edu.tcu.cs.tankwar.constants.*;
import edu.tcu.cs.tankwar.model.*;
import edu.tcu.cs.tankwar.utils.CollisionUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
  private static GameController instance;
  private final List<WallModel> walls;
  private TankModel playerTank;
  private List<EnemyTank> enemyTanks = new ArrayList<>();
  private List<ExplosionModel> explosions = new ArrayList<>();
  private List<MedPackModel> medPacks = new ArrayList<>();
  private double medPackSpawnTimer = 0.0;
  private static final double MED_PACK_SPAWN_INTERVAL = 10.0;
  private Random random = new Random();
  private boolean isGameOver = false;

  private GameController() {
    walls = new ArrayList<>();
  }

  public static GameController getInstance() {
    if (instance == null) {
      instance = new GameController();
    }
    return instance;
  }

  /* Reset game */
  public void reset() {
    walls.clear();
    enemyTanks.clear();
    playerTank = null;
    explosions.clear();
    medPacks.clear();
  }

  /* Update player tank */
  public void updatePlayer(double deltaTime) {
    playerTank.update(deltaTime);
    if (!playerTank.isActive()) {
      isGameOver = true;
    }
  }

  /* Initialize walls */
  public void initializeWalls() {
    /* Create walls around the edges */
    /* 1. Top row */
    for (double x = 0; x < Common.WINDOW_WIDTH; x += Wall.WALL_WIDTH) {
      walls.add(new WallModel(x, 0));
    }
    /* 2. Bottom row */
    for (double x = 0; x < Common.WINDOW_WIDTH; x += Wall.WALL_WIDTH) {
      walls.add(new WallModel(x, Common.WINDOW_HEIGHT - Wall.WALL_HEIGHT));
    }
    /* 3. Left column */
    for (double y = Wall.WALL_HEIGHT; y < Common.WINDOW_HEIGHT - Wall.WALL_HEIGHT; y += Wall.WALL_HEIGHT) {
      walls.add(new WallModel(0, y));
    }
    /* 4. Right column */
    for (double y = Wall.WALL_HEIGHT; y < Common.WINDOW_HEIGHT - Wall.WALL_HEIGHT; y += Wall.WALL_HEIGHT) {
      walls.add(new WallModel(Common.WINDOW_WIDTH - Wall.WALL_WIDTH, y));
    }

    /* Add some obstacles in the middle */
    walls.add(new WallModel(Common.WINDOW_WIDTH/2 - Wall.WALL_WIDTH, Common.WINDOW_HEIGHT/2));
    walls.add(new WallModel(Common.WINDOW_WIDTH/2, Common.WINDOW_HEIGHT/2));
    walls.add(new WallModel(Common.WINDOW_WIDTH/2 + Wall.WALL_WIDTH, Common.WINDOW_HEIGHT/2));
  }

  /* Render walls */
  public void renderWalls(GraphicsContext gc) {
    walls.forEach(wall -> wall.render(gc));
  }

  /* Initialize enemy tanks */
  private double SPAWN_OFFSET = 100;
  public void initializeEnemies() {
    for (int i = 0; i < 2; i++) {
      double x = SPAWN_OFFSET + (i * (Tank.TANK_WIDTH + SPAWN_OFFSET));
      double y = SPAWN_OFFSET;

      if (x + Tank.TANK_WIDTH <= Common.WINDOW_WIDTH) {
        enemyTanks.add(new EnemyTank(x,y));
      }
    }
  }

  /* Update enemies */
  public void updateEnemies(double deltaTime) {
    enemyTanks.forEach(enemy -> enemy.update(deltaTime));

    /* Check for collision */
    checkAllCollisions();
  }

  /* Render enemies */
  public void renderEnemies(GraphicsContext gc) {
    enemyTanks.forEach(enemy -> enemy.render(gc));
    enemyTanks.removeIf(enemy -> !enemy.isActive());
  }

  /* Initialize explosion */
  private void initializeExplosion(double x, double y) {
    explosions.add(new ExplosionModel(x, y));
  }

  /* Create explosion */
  private void createExplosion(TankModel tank) {
    if (!tank.isActive()) {
      /* Calculate center position of the destroyed object */
      double centerX = tank.getPosition().getX() + tank.getWidth()/2;
      double centerY = tank.getPosition().getY() + tank.getHeight()/2;

      /* Offset explosion position */
      double explosionX = centerX - Explosion.EXPLOSION_WIDTH/2;
      double explosionY = centerY - Explosion.EXPLOSION_HEIGHT/2;

      initializeExplosion(explosionX, explosionY);
    }
  }

  /* Render explosions */
  public void renderExplosions(GraphicsContext gc) {
    explosions.forEach(explosion -> explosion.render(gc));
  }

  /* Update explosion */
  public void updateExplosion(double deltaTime) {
    explosions.forEach(explosion -> explosion.update(deltaTime));
    explosions.removeIf(explosion -> !explosion.isActive());
  }

  /* Check for medpack collecting */
  private void checkMedPackCollision() {
    for (MedPackModel medPack : medPacks) {
      if (CollisionUtil.checkMedTankCollision(medPack, getPlayerTank())) {
        getPlayerTank().setHealth(Tank.TANK_MAX_HEALTH);
        medPacks.remove(medPack);
        break;
      }
    }
  }

  /* Render medpacks */
  public void renderMedPacks(GraphicsContext gc) {
    medPacks.forEach(medPack -> medPack.render(gc));
  }

  /* Update medpack */
  public void updateMedPacks(double deltaTime) {
    // Update spawn timer
    medPackSpawnTimer += deltaTime;
    if (medPackSpawnTimer >= MED_PACK_SPAWN_INTERVAL) {
      spawnMedPack();
      medPackSpawnTimer = 0.0;
    }

    // Update existing medpacks
    medPacks.forEach(medPack -> medPack.update(deltaTime));
    checkMedPackCollision();
    medPacks.removeIf(medPack -> !medPack.isActive());
  }

  private void spawnMedPack() {
    if (medPacks.size() >= 3) {
      return;
    }

    // Try to find valid spawn location
    int maxAttempts = 20;
    while (maxAttempts > 0) {
      double x = random.nextDouble() * (Common.WINDOW_WIDTH - MedPack.MEDPACK_WIDTH);
      double y = random.nextDouble() * (Common.WINDOW_HEIGHT - MedPack.MEDPACK_HEIGHT);
      Point2D spawningPosition = new Point2D(x, y);

      // Check if location overlaps with any wall
      boolean validLocation = true;
      for (WallModel wall : walls) {
        if (CollisionUtil.checkWallCollision(new MedPackModel(x, y), spawningPosition)) {
          validLocation = false;
          break;
        }
      }

      if (validLocation) {
        medPacks.add(new MedPackModel(x, y));
        break;
      }
      maxAttempts--;
    }
  }

  /* Render game over */
  public void renderGameOver(GraphicsContext gc) {
    gc.setFill(Color.RED);
    gc.setFont(new Font(48));
    gc.fillText("GAME OVER", Common.WINDOW_WIDTH/2 - 100, Common.WINDOW_HEIGHT/2);
  }

  /* Check for collision */
  private void checkAllCollisions() {
    List<MissileModel> playerMissiles = playerTank.getMissiles();

    /* Check if player missiles hitting enemy tanks */
    for (MissileModel missile : playerMissiles) {
      for (EnemyTank enemyTank : enemyTanks) {
        if (missile.isActive() && enemyTank.isActive()
                && CollisionUtil.checkMissileTankCollision(missile, enemyTank)) {
            missile.setActive(false);
            enemyTank.damage(Missile.MISSILE_DAMAGE);
            createExplosion(enemyTank);
        }
      }
    }

    /* Check if enemy missiles hitting player */
    for (EnemyTank enemyTank : enemyTanks) {
      List<MissileModel> enemyMissiles = enemyTank.getMissiles();
      for (MissileModel missile : enemyMissiles) {
        if (missile.isActive() && playerTank.isActive() &&
                CollisionUtil.checkMissileTankCollision(missile, playerTank)) {
          /* Handle collision */
          missile.setActive(false);
          playerTank.damage(Missile.MISSILE_DAMAGE);
          createExplosion(playerTank);
        }
      }
    }
  }

  /* Getters and setters */
  public List<WallModel> getWalls() {
    return walls;
  }

  public TankModel getPlayerTank() {
    return playerTank;
  }

  public void setPlayerTank(TankModel playerTank) {
    this.playerTank = playerTank;
  }

  public boolean isGameOver() {
    return isGameOver;
  }

}
