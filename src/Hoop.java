import java.awt.*;

public class Hoop {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;
    public int yh;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Hoop(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =5;
        dy =0;
        width = 160;
        height = 240;
        isAlive = true;

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos >= 1000-width){//bounce off right wall
            dx=-dx;
        }
        if (xpos <= 0){//bounce off left wall
            dx=-dx;
        }
        if (ypos >= 700-height){//bounce off bottom wall
            dy=-dy;
        }
        if (ypos <= 0){//bounce off top wall
            dy=-dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;

        yh= 1;
        hitbox= new Rectangle(xpos+50,ypos+135+yh,width-100,height-215); //make hitbox

    }
}
