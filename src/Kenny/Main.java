package Kenny;

import java.util.ArrayList;

import Kenny.Circle;
import Kenny.Constants;
import Kenny.Enemies;
import Kenny.InputHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application implements Constants
{

    private ArrayList<Enemies> baddies = new ArrayList<Enemies>();
    private InputHandler inputHandler = new InputHandler();
    private int counter = 0;
    private boolean collision;

    @Override
    public void start(Stage primaryStage)
    {
        //loads javafx setup
        //this code is mostly irrelevant and only implements the
        //javafx canvas, root and scene.
        //the screen size and listeners are also added in here

        primaryStage.setTitle("Test");
        Canvas canvas = new Canvas(1100, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 1100, 600);
        scene.setOnKeyPressed(this::KeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);
        primaryStage.setScene(scene);
        gc.setFill(Color.BLUE);
        gc.fillRect(0 , 0, 1100 , 600);
        primaryStage.show();


        Enemies enemy = new Enemies(gc);
        enemy.setRadius(15);
        baddies.add(enemy);
        Circle ball = new Circle();
        ball.setRadius(30);

        //sets loop to 4ms delay, and calls the art loader
        KeyFrame frame = new KeyFrame(Duration.millis(4f), (event) ->
        {
            gc.setFill(Color.WHITE);
            gc.setStroke(Color.DARKBLUE);
            gc.fillRect(insets , insets, gameWidth , gameHeight);
            gc.strokeRect(insets , insets, gameWidth , gameHeight);
           // KeyCode action = inputHandler.getAction(index);

            if (inputHandler.isInStack(KeyCode.S))
                ball.incrementY(4);

            if (inputHandler.isInStack(KeyCode.W))
                ball.incrementY(-4);

            if (inputHandler.isInStack(KeyCode.A))
                ball.incrementX(-4);

            if (inputHandler.isInStack(KeyCode.D))
                ball.incrementX(4);

            if(ball.getXPos() + (circleW) >= gameWidth + insets)
                ball.setXPos((gameWidth + insets) - (circleW) - 1);

            if(ball.getXPos() <= insets)
                ball.setXPos(insets);

            if(ball.getYPos() + (circleH) >= gameHeight + insets)
                ball.setYPos((gameHeight + insets) - (circleH) - 1);

            if(ball.getYPos() <= insets)
                ball.setYPos(insets);

            gc.setFill(Color.BLACK);
            gc.fillOval(ball.getXPos(), ball.getYPos(), ball.getRadius(), ball.getRadius());

            for(int i = baddies.size() - 1; i >= 0; i--)
            {
                if(ball.intersects((baddies.get(i))))
                    System.out.println("hit");
                baddies.get(i).draw();
                baddies.get(i).move();
            }
        });

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(frame);
        timeline.play();

    }

    public void KeyPressed(KeyEvent e)
    {
        inputHandler.keyPressed(e);
    }

    public void onKeyReleased(KeyEvent e)
    {
        inputHandler.keyReleased(e);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

