import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class InputHandler
{
    private ArrayList<KeyCode> stack = new ArrayList<>();
    boolean inStack = false;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    //returns if a key is down and returns the keyCode if it is
    public boolean isKeyDown(KeyCode keyCode)
    {
        return keys.get(keyCode);
    }

    public void keyPressed(KeyEvent e)                  // Sets the index of the key pressed's keyCode to true
    {                                                   // adds the current key pressed's keyCode to the stack
        keys.put(e.getCode(), true);
        addToStack(e.getCode());
    }

    public void keyReleased(KeyEvent e)                 //Sets the index of the key released's keyCode to false
    {                                                   //removes any key released from the stack
        keys.put(e.getCode(), false);
        removeFromStack(e.getCode());
    }

    public void addToStack(KeyCode x)                   //If the stack size is 0 it just adds the integer to the arraylist
    {                                                   //Otherwise it scans through the list to see if it already exists
        if (stack.size() == 0)                          //If it does exist the element is not added
            stack.add(x);
        else
        {
            inStack = false;
            for(int i = stack.size() - 1; i >= 0; i--)
            {
                if(stack.get(i) == x)
                    inStack = true;
            }
            if (!inStack)
                stack.add(x);
        }
    }

    public void removeFromStack(KeyCode x)             //Searches through the stack and removes the element that matches the parameter value
    {
        for(int i = stack.size() - 1; i >= 0; i--)
        {
            if(stack.get(i)== x)
                stack.remove(i);
        }
    }

    public KeyCode getAction()
    {
        if (stack.size() == 0)
            return KeyCode.DEAD_ABOVEDOT;
        return stack.get(0);
    }

    public ArrayList<KeyCode> getStack()
    {
        return stack;
    }

    //returns an element at a specific index of stack
    public KeyCode getAction(int a)
    {
        if ((stack.size() == 0) || (stack.size() < a))
            return KeyCode.DEAD_ABOVEDOT;
        System.out.println(getStack());
        return stack.get(a);
    }

    public boolean isInStack(KeyCode e)
    {
        for(int i = stack.size() - 1; i >= 0; i--)
        {
            if(stack.get(i) == e)
               return true;
        }
        return false;
    }

    //returns the size of the stack
    public int getSize()
    {
        return stack.size();
    }


}
