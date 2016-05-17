import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;


import javax.imageio.ImageIO;

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
        Enemies enemy = new Enemies(gc);
        enemy.setRadius(15);
        Circle ball = new Circle();
        ball.setRadius(30);
        baddies.add(enemy);
        //baddies.add(new Enemies(gc));
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 1100, 600);
        scene.setOnKeyPressed(this::KeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);
        primaryStage.setScene(scene);
        gc.setFill(Color.BLUE);
        gc.fillRect(0 , 0, 1100 , 600);


        primaryStage.show();


        //sets loop to 4ms delay, and calls the art loader
        KeyFrame frame = new KeyFrame(Duration.millis(4f), (event) ->
        {

            gc.setFill(Color.WHITE);
            gc.setStroke(Color.DARKBLUE);
            gc.fillRect(insets , insets, gameWidth , gameHeight);
            gc.strokeRect(insets , insets, gameWidth , gameHeight);
           // KeyCode action = inputHandler.getAction(index);


            if (inputHandler.isInStack(KeyCode.S))
                ball.incrementPlayerY(4);

            if (inputHandler.isInStack(KeyCode.W))
                ball.incrementPlayerY(-4);

            if (inputHandler.isInStack(KeyCode.A))
                ball.incrementPlayerX(-4);

            if (inputHandler.isInStack(KeyCode.D))
                ball.incrementPlayerX(4);

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
					//if(ball.getPlayerHitbox().intersects(baddies.get(i).getEnemyHitbox().getBoundsInLocal()))
                    System.out.println(baddies.get(i).getXPos() + "e");
                    System.out.println(ball.getXPos());
                    if(ball.intersects((baddies.get(i))))
                        primaryStage.close();

                    baddies.get(i).draw();
                    baddies.get(i).move();
                  //  ball.setXPos(baddies.get(i).getXPos());
                   // ball.setYPos(baddies.get(i).getYPos());

                }

//


        });

        //this just puts the timeline  in motion
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

