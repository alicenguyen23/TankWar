package edu.tcu.cs.tankwar.controller;

import edu.tcu.cs.tankwar.model.TankModel;
import edu.tcu.cs.tankwar.model.WallModel;

import java.util.ArrayList;
import java.util.List;

public class GameController {
  private static GameController instance;
  private List<WallModel> walls;
  private TankModel playerTank;

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
    playerTank = null;
  }

  /* Getters and setters */
  public List<WallModel> getWalls() {
    return walls;
  }

  public void setWalls(List<WallModel> walls) {
    this.walls = walls;
  }

  public void addWall(WallModel wall) {
    walls.add(wall);
  }

  public TankModel getPlayerTank() {
    return playerTank;
  }

  public void setPlayerTank(TankModel playerTank) {
    this.playerTank = playerTank;
  }

}
