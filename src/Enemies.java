import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class Enemies extends Circle implements Constants
{
    private int xPos = 26;
    private int yPos = 26;
    private int vertOrSide;
    private int maxOrMin;
    private int directionX = 0;
    private int directionY = 0;
    private boolean isSide = false, isVert = false;
    private int enemyW = 15;
    private int enemyH = 15;
   // private Rectangle enemyHitbox = new Rectangle(xPos, yPos, enemyW, enemyH);

    private GraphicsContext graphicsContext;

    public Enemies ()
    {

    }

    public Enemies(GraphicsContext gc)
    {
        graphicsContext = gc;
        this.vertOrSide = (int)(Math.random() * 2)+ 1;
        this.maxOrMin = (int)(Math.random() * 2)+ 1;
        createEnemy();
    }

    public void createEnemy()
    {
        if(vertOrSide == 1)
            createSideEnemy();
        else
            createVertEnemeny();
    }

    public void createSideEnemy()
    {
        isSide = true;
        if(maxOrMin == 1)
        {
            xPos = insets;
            directionX = 1;
        }
        else
        {
            xPos = gameWidth + insets - enemyW - 1;
            directionX = -1;
        }
        yPos = (int)(Math.random() * gameHeight - enemyH) + insets;
        isSide = true;
    }

    public void createVertEnemeny()
    {
        isVert = false;
        if(maxOrMin == 1)
        {
            yPos = insets;
            directionY = 1;
        }
        else
        {
            yPos = gameHeight + insets - enemyH - 1;
            directionY = -1;
        }
        xPos = (int)(Math.random() * gameWidth - enemyW) + insets;
        isVert = true;
    }

    public void move()
    {
        if (isSide && directionX == -1)
        {
            xPos -= 3;

            if (xPos <= insets)
            {
                xPos = insets;
                directionX = 1;
            }
        }
        if (isSide && directionX == 1)
        {
            xPos += 3;

            if (xPos + (enemyW) >= gameWidth + insets)
            {
                xPos = (gameWidth + insets) - enemyW - 1;
                directionX = -1;
            }
        }
        if (isVert && directionY == -1)
        {
            yPos -= 3;

            if (yPos <= insets)
            {
                yPos = insets;
                directionY = 1;
            }
        }
        if (isVert && directionY == 1)
        {
            yPos += 3;

            if (yPos + (enemyH) >= gameHeight + insets)
            {
                yPos = (gameHeight + insets) - enemyH - 1;
                directionY = -1;
            }
        }
    }

    public void draw()
    {
        graphicsContext.setFill(Color.GRAY);
        graphicsContext.fillOval(xPos, yPos, this.getRadius(), this.getRadius());
        graphicsContext.strokeOval(xPos, yPos, this.getRadius(), this.getRadius());
    }

    public void incrementX(int a)
    {
        xPos += a;
    }

    public void incrementY(int a)
    {
        yPos += a;
    }

    public int getXPos()
    {
        return xPos;
    }

    public int getYPos()
    {
        return yPos;
    }


    public int getEnemeyW()
    {
        return enemyW;
    }

    public int getEnemeyH()
    {
        return enemyH;
    }


}
