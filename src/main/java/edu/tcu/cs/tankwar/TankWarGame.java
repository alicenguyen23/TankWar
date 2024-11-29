package edu.tcu.cs.tankwar;

import edu.tcu.cs.tankwar.constants.Common;
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
    private final long lastUpdate = 0;
    /* Create a tank object at the bottom center of the canvas */
    private final TankModel playerTankModel = new TankModel(Common.WINDOW_WIDTH /2.0, Common.WINDOW_HEIGHT - Common.WINDOW_BOTTOM_OFFSET);

    @Override
    public void start(Stage stage) {
        /* Create a canvas to display game */
        gameCanvas = new Canvas(Common.WINDOW_WIDTH, Common.WINDOW_HEIGHT);
        gc = gameCanvas.getGraphicsContext2D();

        /* Create a container for the canvas */
        StackPane root = new StackPane(gameCanvas);
        Scene scene = new Scene(root, Common.WINDOW_WIDTH, Common.WINDOW_HEIGHT);

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
        stage.setTitle(Common.TITLE);
        stage.setScene(scene);
        stage.show();

        /* Start the game loop */
        gameLoop.start();

    }

    private void update(double deltaTime) {
        /* TODO: Update game objects */
        playerTankModel.update(deltaTime);
    }

    private void render() {
        /* Clear the canvas */
        gc.clearRect(0, 0, Common.WINDOW_WIDTH, Common.WINDOW_HEIGHT);

        /* TODO: Render game objects */
        playerTankModel.render(gc);
    }

    public static void main(String[] args) {
        launch();
    }
}