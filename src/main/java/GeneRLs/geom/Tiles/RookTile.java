package GeneRLs.geom.Tiles;

import GeneRLs.Main;
import GeneRLs.core.Applet;
import GeneRLs.storage.Vector;
import processing.core.PApplet;
import processing.core.PImage;

public class RookTile extends GeneratorTile {

    public RookTile(Applet applet, float x, float y, float size) {
        super(applet, x, y, size);
        String commonPath = applet.sketchPath("src/main/java/GeneRLs/data/");
        this.sprite = applet.loadShape(commonPath + "rook.svg");
    }

    public RookTile(Applet applet, Vector pos, float size) {
        this(applet, pos.x, pos.y,size);
    }
}