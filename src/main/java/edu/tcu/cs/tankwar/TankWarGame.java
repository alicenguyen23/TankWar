package edu.tcu.cs.tankwar;

import edu.tcu.cs.tankwar.constants.Common;
import edu.tcu.cs.tankwar.constants.Tank;
import edu.tcu.cs.tankwar.model.TankModel;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TankWarGame extends Application {
    private Canvas gameCanvas; /* for graphics/game rendering */
    private GraphicsContext gc;
    private long lastUpdate = System.nanoTime();
    /* Create a tank object at the bottom center of the canvas */
    private final TankModel playerTank = new TankModel(Common.WINDOW_WIDTH/2.0, Common.WINDOW_HEIGHT - Common.WINDOW_BOTTOM_OFFSET);
    /* Movement control */
    private boolean wPressed = false; /* Move forward */
    private boolean sPressed = false; /* Move backward */
    private boolean aPressed = false; /* Move left */
    private boolean dPressed = false; /* Move right */
    private boolean qPressed = false; /* Rotate left */
    private boolean ePressed = false; /* Rotate right */

    @Override
    public void start(Stage stage) {
        /* Create a canvas to display game */
        gameCanvas = new Canvas(Common.WINDOW_WIDTH, Common.WINDOW_HEIGHT);
        gc = gameCanvas.getGraphicsContext2D();

        /* Create a container for the canvas */
        Scene scene = getScene();

        /* Game loop (continuously check for any object update) */
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                /* Calculate delta time (convert to sec) */
                double deltaTime = (currentNanoTime - lastUpdate) / 1_000_000_000.0;

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
            switch (e.getCode()) {
                case W -> wPressed = true;
                case S -> sPressed = true;
                case A -> aPressed = true;
                case D -> dPressed = true;
                case Q -> qPressed = true;
                case E -> ePressed = true;
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
            }
        });
        return scene;
    }

    private void update(double deltaTime) {
        /* TODO: Update game objects */
        if (wPressed) { /* Move up */
            playerTank.move(0, -Tank.TANK_SPEED * deltaTime);
        }
        if (sPressed) { /* Move down */
            playerTank.move(0, Tank.TANK_SPEED * deltaTime);
        }
        if (aPressed) { /* Move left */
            playerTank.move(-Tank.TANK_SPEED * deltaTime, 0);
        }
        if (dPressed) { /* Move right */
            playerTank.move(Tank.TANK_SPEED * deltaTime, 0);
        }
        if (qPressed) playerTank.rotate(-45 * deltaTime); /* Rotate left */
        if (ePressed) playerTank.rotate(45 * deltaTime); /* Rotate right */

        playerTank.update(deltaTime);
    }

    private void render() {
        /* Clear the canvas */
        gc.clearRect(0, 0, Common.WINDOW_WIDTH, Common.WINDOW_HEIGHT);

        /* TODO: Render game objects */
        playerTank.render(gc);
    }

    public static void main(String[] args) {
        launch();
    }
}