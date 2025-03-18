package GeneRLs.geom.Tiles;

import GeneRLs.core.Applet;
import GeneRLs.storage.Color;
import GeneRLs.storage.ColorType;
import GeneRLs.storage.Vector;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

import static processing.core.PApplet.str;

public class Tile {
    public enum State {
        UNCLAIMED, BLUE, RED
    }

    protected float x, y; // Canvas coordinates
    protected storage.IntVector boardPosition;
    protected float size;
    protected State state;
    protected Applet applet;
    public PShape sprite; // Null by default; use for special tiles.
    public int army = 0;

    public Tile(Applet applet, float x, float y, float size) {
        this.applet = applet;
        this.x = x;
        this.y = y;
        this.size = size;
        this.state = State.UNCLAIMED;
    }

    public Tile(Applet applet, Vector pos, float size) {
        this(applet,pos.x,pos.y,size);
    }

    public void setBoardPosition(storage.IntVector pos) {
        boardPosition = pos;
    }

    public storage.IntVector getBoardPosition() {
        return boardPosition;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public Vector getPosition() {
        return new Vector(x, y);
    }

    public void draw() {
        // Base tile rendering
        Color color = switch (state) {
            case UNCLAIMED -> new Color(255,0,65,90);
            case BLUE -> new Color(ColorType.BLUE);
            case RED -> new Color(ColorType.RED);
        };
        applet.fill(color);

        color.setSaturation(50);
      //  color.setAlpha(70);
        if (state == State.UNCLAIMED) {
            applet.strokeWeight(4);
            applet.stroke(color);
        }

        applet.rect(x - size/2f, y - size/2f, x + size/2f, y + size/2f); //,4 for curved slows down for P2D renderer
        // probably because I'm running this on an M1 mac?
        drawSprite();

        if (army > 0) {
            applet.fill(0); // black text for good readability
            applet.textAlign(PApplet.CENTER, PApplet.CENTER);
            applet.textFont(applet.createFont("Lato Bold",150));

            float desiredWidth = size * 0.8f; // 80% of tile width
            float txtSize = size / 1.5f;
            applet.textSize(txtSize);

            // Reduce text size until it fits within tile
            while (applet.textWidth(str(army)) > desiredWidth) {
                applet.textSize(--txtSize);
                if(txtSize < 10) break; // minimal readable size
            }

            applet.text(army, x, y + size/15f);
        }
    }

    public void drawSprite(){

    }
}
