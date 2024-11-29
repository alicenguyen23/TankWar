package edu.tcu.cs.tankwar;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TankWarGame extends Application {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private Canvas gameCanvas; /* for graphics/game rendering */
    private GraphicsContext gc;
    private long lastUpdate = 0;

    @Override
    public void start(Stage stage) {
        /* Create a canvas to display game */
        gameCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        gc = gameCanvas.getGraphicsContext2D();

        /* Create a container for the canvas */
        StackPane root = new StackPane(gameCanvas);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

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
            }
        };

        /* Set up stage */
        stage.setTitle("Tank War Game");
        stage.setScene(scene);
        stage.show();

        /* Start the game loop */
        gameLoop.start();

    }

    private void update(double deltaTime) {
        // TODO: Update game objects
    }

    private void render() {
        // Clear the canvas
        gc.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // TODO: Render game objects
    }

    public static void main(String[] args) {
        launch();
    }
}