package GeneRLs;
import GeneRLs.geom.Board;
import GeneRLs.geom.Game;
import GeneRLs.geom.Tiles.CrownTile;
import GeneRLs.geom.Tiles.RookTile;
import GeneRLs.geom.Tiles.Tile;
import GeneRLs.storage.ColorType;
import GeneRLs.storage.Vector;
import GeneRLs.util.io.PyClient;
import com.hamoid.*;
import GeneRLs.core.Applet;
import GeneRLs.directions.Directions;
import processing.core.PApplet;
import processing.core.PFont;
import processing.event.MouseEvent;

import java.io.File;
import processing.data.JSONObject;


public class Main extends Applet {
    public static PFont myFont, italics;
    public VideoExport videoExport;
    public static int WIDTH = 1000;
    public static int HEIGHT = 1000;

    PyClient client;

    public void setup(){
        String commonPath = sketchPath("src/main/java/GeneRLs/data/");
        myFont = createFont(commonPath + "cmunbmr.ttf", 150, true);
        italics = createFont(commonPath + "cmunbmo.ttf", 150, true);
        textFont(myFont);
      //  Directions.init(this);
       // client = new PyClient(this, "localhost", 5001);

        videoExport = new VideoExport(this,"test.mp4");
        String ffmpegPath = sketchPath("library/ffmpeg");
        File ffmpegFile = new File(ffmpegPath);
        if (!ffmpegFile.exists()) {
            println("⚠ FFMPEG not found at: " + ffmpegPath);
        } else {
            println("✅ Found FFMPEG at: " + ffmpegPath);
        }
        videoExport.setFfmpegPath(ffmpegPath);

        videoExport.setQuality(85,0);
        videoExport.setFrameRate(60);
        frameRate(60);
        //surface.setVisible(false);
       // videoExport.startMovie();
        init();
    }


    public void settings() {
        size(WIDTH, HEIGHT, P2D);
        //fullScreen();// P2D freakishly slow
        smooth(8);
    }


    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{Main.class.getCanonicalName()};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }

    Game game;

    public void init(){
        game = new Game(this);
    }

    @Override
    public void draw(){
        super.draw();
        //beginRecord(SVG, "frame-####.svg");
      //  println(getMouseX(),getMouseY(),180 + 360 * new Vector(getMouseX(),getMouseY()).mag()/new Vector(WIDTH,HEIGHT).mag());

       // client.send(coords);

        println(frameRate);
        pushMatrix();
        translate(20,-50);
        game.draw();
        popMatrix();

        noFill();
        stroke(ColorType.MAGENTA);
        strokeWeight(10);



       /* JSONObject response = client.receive();

        while (response == null){
            delay(10);
            response = client.receive();
        }
        int curCol = response.getInt("col");

        strokeWeight(10);
        stroke(curCol,255,255);
        noFill();

        circle(getMouseX(),getMouseY(),100); */


        //videoExport.saveFrame();
    }

    public static float offsetX = 0;
    public static float offsetY = 0;
    public static float speed = 10; // how fast to move viewport
    boolean[] isPressed = new boolean[50];

    public void update(){
        if (isPressed[LEFT]){
            offsetX += speed;
        }
        if (isPressed[RIGHT]){
            offsetX -= speed;
        }
        if (isPressed[UP]){
            offsetY -= speed;
        }
        if (isPressed[DOWN]){
            offsetY += speed;
        }
    }

    public void keyPressed() {
        if (key == 'q') {
            videoExport.endMovie();
            exit();
        }

        isPressed[keyCode] = true;
    }

    public void keyReleased() {
        isPressed[keyCode] = false;
    }

    public void mouseWheel(MouseEvent event) {
        float e = event.getCount();
        // Multiply zoom factor by 1.05 for each "tick".
        // Scrolling up (negative count) zooms in, scrolling down zooms out.
        this.zoom *= pow(1.05f, -e);
        // Optional: print the zoom level to the console for debugging.
        println("Zoom level: " + zoom);
    }
}