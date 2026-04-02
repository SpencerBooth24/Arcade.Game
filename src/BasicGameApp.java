//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too

   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
    public Image ballPic;
    public Image hoopPic;
	public Image bronPic;
    public Image jordanPic;
    public Image kobePic;
    public Image winScreen;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
    private Ball ball;
	private Hoop hoop;
    private Lebron bron;
    private Jordan jordan;
    private Kobe kobe;
    private Win win;
    public int randq;
    public int randw;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
        int randx = (int)(Math.random()*700);
        int randy = (int)(Math.random()*500);


      //variable and objects
      //create (construct) the objects needed for the game and load up
        ballPic = Toolkit.getDefaultToolkit().getImage("ball.jpg"); //load the picture
        ball = new Ball(randx, randy, randq+5, randw+5);
        hoopPic = Toolkit.getDefaultToolkit().getImage("hoop.png"); //load the picture
        hoop = new Hoop(10,100);


		bronPic = Toolkit.getDefaultToolkit().getImage("bron.png"); //load the picture
		bron = new Lebron(100,500);
        jordanPic = Toolkit.getDefaultToolkit().getImage("jordan.png");
        jordan = new Jordan(400,500);
        kobePic = Toolkit.getDefaultToolkit().getImage("kobe.png");
        kobe = new Kobe(700,500);
        winScreen=Toolkit.getDefaultToolkit().getImage("Win.png");
        win= new Win(0,0);


	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
        ball.move();
        hoop.move();
		bron.move();
        jordan.move();
        kobe.move();

        if (ball.hitbox.intersects(bron.hitbox)||ball.hitbox.intersects(jordan.hitbox)||ball.hitbox.intersects(kobe.hitbox)){
            ball.dx=-ball.dx;
            ball.dy=-ball.dy;
            randq = (int)(Math.random()*10); //ball intersects with characters
            randw = (int)(Math.random()*10);
        }


       if (ball.hitbox.intersects(hoop.hitbox)){
           win.isAlive= true; //winscreen
       }

	}
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout




      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.

       canvas.addMouseListener(this);
       canvas.addKeyListener(this);


      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the characters

        g.drawImage(hoopPic,hoop.xpos,150,hoop.width,hoop.height,null);
		g.drawImage(bronPic, bron.xpos, bron.ypos, bron.width, bron.height, null);
        g.drawImage(jordanPic,jordan.xpos,jordan.ypos,jordan.width,jordan.height,null);
        g.drawImage(kobePic,kobe.xpos,kobe.ypos,kobe.width, kobe.height, null);
        g.drawImage(ballPic,ball.xpos,ball.ypos,ball.width,ball.height,null);
        g.drawRect(hoop.hitbox.x,hoop.hitbox.y,hoop.hitbox.width,hoop.hitbox.height);
        g.drawRect(bron.hitbox.x,bron.hitbox.y,bron.hitbox.width,bron.hitbox.height);

        if (win.isAlive==true){
            g.drawImage(winScreen,0,0,1000,700,null); //winscreen
        }
		g.dispose();

		bufferStrategy.show();

	}


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}