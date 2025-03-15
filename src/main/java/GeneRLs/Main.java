package GeneRLs;
import GeneRLs.storage.Vector;
import com.hamoid.*;
import GeneRLs.core.Applet;
import GeneRLs.directions.Directions;
import processing.core.PApplet;
import processing.core.PFont;
import processing.event.MouseEvent;

import java.io.File;
import processing.net.Client;
import processing.data.JSONObject;


public class Main extends Applet {
    public static PFont myFont, italics;
    public VideoExport videoExport;
    public static int WIDTH = 1420;
    public static int HEIGHT = 780;

    Client client;

    public void setup(){
        String commonPath = sketchPath("src/main/java/GeneRLs/data/");
        myFont = createFont(commonPath + "cmunbmr.ttf", 150, true);
        italics = createFont(commonPath + "cmunbmo.ttf", 150, true);
        Directions.init(this);
        client = new Client(this, "localhost", 5001);

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
      //  videoExport.startMovie();


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

    @Override
    public void draw(){
        super.draw();
        //beginRecord(SVG, "frame-####.svg");
        println(getMouseX(),getMouseY(),180 + 360 * new Vector(getMouseX(),getMouseY()).mag()/new Vector(WIDTH,HEIGHT).mag());
        JSONObject coords = new JSONObject();
        coords.put("mouseX", getMouseX());
        coords.put("mouseY", getMouseY());
        coords.put("WIDTH", WIDTH);
        coords.put("HEIGHT", HEIGHT);

        client.write(coords.toString().replaceAll("\\s+", "") + "\n");

        String buffer = "";
        float curCol = 0;
        // Receive angle from Python
        if (client.available() > 0) {
            buffer += client.readString();
            int newLine = buffer.indexOf("\n");
            if (newLine != -1) {
                String jsonStr = buffer.substring(0, newLine);
                buffer = buffer.substring(newLine + 1);
                JSONObject angleJSON = parseJSONObject(jsonStr);
                if (angleJSON != null) {
                    curCol = angleJSON.getFloat("colour");
                }
            }
        }

        strokeWeight(10);
        stroke(curCol,255,255);
        noFill();

        circle(getMouseX(),getMouseY(),100);



        //videoExport.saveFrame();
    }


    public void keyPressed() {
        if (key == 'q') {
            videoExport.endMovie();
            exit();
        }
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