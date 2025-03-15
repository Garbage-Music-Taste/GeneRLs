package GeneRLs;
import com.hamoid.*;
import GeneRLs.core.Applet;
import GeneRLs.directions.Directions;
import processing.core.PApplet;
import processing.core.PFont;
import processing.event.MouseEvent;

import java.io.File;

public class Main extends Applet {
    public static PFont myFont, italics;
    public VideoExport videoExport;
    public static int WIDTH = 1420;
    public static int HEIGHT = 780;

    public void setup(){
        String commonPath = sketchPath("src/main/java/GeneRLs/data/");
        myFont = createFont(commonPath + "cmunbmr.ttf", 150, true);
        italics = createFont(commonPath + "cmunbmo.ttf", 150, true);
        Directions.init(this);

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
        circle(getMouseX(),getMouseY(),100);
        println(getMouseX(),getMouseY());
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