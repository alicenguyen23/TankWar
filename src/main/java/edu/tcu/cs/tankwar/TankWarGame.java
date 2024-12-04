package edu.tcu.cs.tankwar;

import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.constants.Tank;
import edu.tcu.cs.tankwar.constants.Wall;
import edu.tcu.cs.tankwar.controller.GameController;
import edu.tcu.cs.tankwar.model.HealthBarModel;
import edu.tcu.cs.tankwar.model.TankModel;
import edu.tcu.cs.tankwar.model.WallModel;
import edu.tcu.cs.tankwar.utils.TimeUtil;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TankWarGame extends Application {
    private Canvas gameCanvas; /* for graphics/game rendering */
    private GraphicsContext gc;
    private long lastUpdate = System.nanoTime();
    /* Movement control */
    private boolean wPressed = false; /* Move forward */
    private boolean sPressed = false; /* Move backward */
    private boolean aPressed = false; /* Move left */
    private boolean dPressed = false; /* Move right */
    private boolean qPressed = false; /* Rotate left */
    private boolean ePressed = false; /* Rotate right */
    private boolean spacePressed = false; /* Shoot missiles */
    /* Game controller */
    private GameController gameController = GameController.getInstance();
    /* Health bar */
    HealthBarModel healthBar;

    @Override
    public void start(Stage stage) {
        /* Create a canvas to display game */
        gameCanvas = new Canvas(Common.WINDOW_WIDTH, Common.WINDOW_HEIGHT);
        gc = gameCanvas.getGraphicsContext2D();

        /* Create a container for the canvas */
        Scene scene = getScene();

        /* Create a tank object at the bottom center of the canvas */
        TankModel playerTank = new TankModel(Common.WINDOW_WIDTH/2.0, Common.WINDOW_HEIGHT - Common.WINDOW_BOTTOM_OFFSET, true);
        gameController.setPlayerTank(playerTank);

        /* Initialize walls */
        gameController.initializeWalls();

        /* Initialize enemies */
        gameController.initializeEnemies();

        /* Game loop (continuously check for any object update) */
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                /* Calculate delta time (convert to sec) */
                double deltaTime = TimeUtil.nanoToSeconds(currentNanoTime - lastUpdate);

                /* Update game state */
                update(deltaTime);
                /* Render */
                render();
                /* Update the last update time */
                lastUpdate = currentNanoTime;
            }
        };

        /* Set up stage */
        stage.setTitle(Common.TITLE);
        stage.setScene(scene);
        stage.show();

        /* Start the game loop */
        gameLoop.start();

    }

    private Scene getScene() {
        StackPane root = new StackPane(gameCanvas);
        Scene scene = new Scene(root, Common.WINDOW_WIDTH, Common.WINDOW_HEIGHT);

        /* Key handlers for movement control */
        scene.setOnKeyPressed(e -> {
            if (!gameController.isGameOver()) {
                switch (e.getCode()) {
                    case W -> wPressed = true;
                    case S -> sPressed = true;
                    case A -> aPressed = true;
                    case D -> dPressed = true;
                    case Q -> qPressed = true;
                    case E -> ePressed = true;
                    case SPACE -> spacePressed = true;
                }
            }
        });
        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W -> wPressed = false;
                case S -> sPressed = false;
                case A -> aPressed = false;
                case D -> dPressed = false;
                case Q -> qPressed = false;
                case E -> ePressed = false;
                case SPACE -> spacePressed = false;
            }
        });
        return scene;
    }

    private void update(double deltaTime) {
        if (wPressed) gameController.getPlayerTank().move(deltaTime, "up"); /* Move up */
        if (sPressed) gameController.getPlayerTank().move(deltaTime, "down"); /* Move down */
        if (aPressed) gameController.getPlayerTank().move(deltaTime, "left"); /* Move left */
        if (dPressed) gameController.getPlayerTank().move(deltaTime, "right"); /* Move right */
        if (qPressed) gameController.getPlayerTank().rotate(-45 * deltaTime); /* Rotate left */
        if (ePressed) gameController.getPlayerTank().rotate(45 * deltaTime); /* Rotate right */
        if (spacePressed) gameController.getPlayerTank().shoot(TimeUtil.nanoToSeconds(lastUpdate)); /* Shoot missiles */

        if (gameController.getPlayerTank() != null) {
            gameController.updatePlayer(deltaTime);
            gameController.updateEnemies(deltaTime);
            gameController.updateExplosion(deltaTime);
            gameController.updateMedPacks(deltaTime);
        }
    }

    private void render() {
        /* Clear the canvas */
        gc.clearRect(0, 0, Common.WINDOW_WIDTH, Common.WINDOW_HEIGHT);

        /* Render walls */
        gameController.renderWalls(gc);
        /* Render player tank */
        if (gameController.getPlayerTank() != null) {
            gameController.getPlayerTank().render(gc);
            healthBar = new HealthBarModel(gameController.getPlayerTank());
            healthBar.render(gc);
        }
        /* Render enemies */
        gameController.renderEnemies(gc);
        /* Render explosions */
        gameController.renderExplosions(gc);
        /* Render medpacks */
        gameController.renderMedPacks(gc);
        /* Render game over state */
        if (gameController.isGameOver()) {
            gameController.renderGameOver(gc);
            gameController.reset();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}