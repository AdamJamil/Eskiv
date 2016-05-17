package Kenny;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemies extends Circle implements Constants
{
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
            this.setXPos(insets);
            directionX = 1;
        }
        else
        {
            setXPos(gameWidth + insets - enemyW - 1);
            directionX = -1;
        }
        setYPos((int)(Math.random() * gameHeight - enemyH) + insets);
        isSide = true;
    }

    public void createVertEnemeny()
    {
        isVert = false;
        if(maxOrMin == 1)
        {
            setYPos(insets);
            directionY = 1;
        }
        else
        {
            setYPos(gameHeight + insets - enemyH - 1);
            directionY = -1;
        }
        setXPos((int)(Math.random() * gameWidth - enemyW) + insets);
        isVert = true;
    }

    public void move()
    {
        if (isSide && directionX == -1)
        {
            incrementX(-3);

            if (getXPos() <= insets)
            {
                setXPos(insets);
                directionX = 1;
            }
        }
        if (isSide && directionX == 1)
        {
            incrementX(3);

            if (getXPos() + (enemyW) >= gameWidth + insets)
            {
                setXPos((gameWidth + insets) - enemyW - 1);
                directionX = -1;
            }
        }
        if (isVert && directionY == -1)
        {
            incrementY(-3);

            if (getYPos() <= insets)
            {
                setYPos(insets);
                directionY = 1;
            }
        }
        if (isVert && directionY == 1)
        {
            incrementY(3);

            if (getYPos() + (enemyH) >= gameHeight + insets)
            {
                setYPos((gameHeight + insets) - enemyH - 1);
                directionY = -1;
            }
        }
    }

    public void draw()
    {
        graphicsContext.setFill(Color.GRAY);
        graphicsContext.fillOval(getXPos(), getYPos(), this.getRadius(), this.getRadius());
        graphicsContext.strokeOval(getXPos(), getYPos(), this.getRadius(), this.getRadius());
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
