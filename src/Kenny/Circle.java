package Kenny;

/**
 * Created by 10009354 on 5/17/2016.
 */
public class Circle implements Constants
{
    private int xPos, yPos;
    private int radius;

    public boolean intersects(Circle circle)
    {
        int tempX, tempY;
        for(int i = 0; i <= 359; i++)
        {
            tempX = xPos + Constants.circleW * (1 + (int)(Math.cos(i) * Math.PI / 180));
            tempY = yPos + Constants.circleW * (1 + (int)(Math.sin(i) * Math.PI / 180));

            if(Math.pow((tempX - circle.getCenterX()), 2) + Math.pow((tempY - circle.getCenterY()), 2) <= Math.pow(circle.getRadius(), 2))
                return true;
        }
        return false;
    }

    public boolean intersects(Enemies circle)
    {
        int tempX, tempY;
        for(int i = 0; i <= 359; i++)
        {
            tempX = getCenterX() + getRadius() * (int)(Math.cos(i * Math.PI / 180));
            tempY = getCenterY() + getRadius() * (int)(Math.sin(i * Math.PI / 180));
            System.out.println(Math.pow((tempX - circle.getCenterX()), 2) + Math.pow((tempY - circle.getCenterY()), 2));

            if(Math.pow((tempX - circle.getCenterX()), 2) + Math.pow((tempY - circle.getCenterY()), 2) < Math.pow(circle.getRadius() - 2, 2))
                return true;
        }
        return false;
    }

    public int getCenterX()
    {
        return xPos + radius;
    }

    public int getCenterY()
    {
        return yPos + radius;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int x) {
        this.xPos = x;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int y) {
        this.yPos = y;
    }

    public void incrementX(int x)
    {
        xPos += x;
    }

    public void incrementY(int x)
    {
        yPos += x;
    }

    public int getRadius()
    {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
