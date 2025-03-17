package GeneRLs.geom.Tiles;

import GeneRLs.core.Applet;
import GeneRLs.storage.Vector;

public class MountainTile extends Tile {
    public MountainTile(Applet applet, float x, float y, float size) {
        super(applet, x, y, size);
        String commonPath = applet.sketchPath("src/main/java/GeneRLs/data/");
        this.sprite = applet.loadShape(commonPath + "mountain.svg");
    }

    public MountainTile(Applet applet, Vector pos, float size) {
        this(applet, pos.x, pos.y, size);
    }
}
