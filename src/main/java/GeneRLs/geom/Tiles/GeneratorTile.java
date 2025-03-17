package GeneRLs.geom.Tiles;

import GeneRLs.core.Applet;
import GeneRLs.storage.Vector;

public class GeneratorTile extends Tile {
    public GeneratorTile(Applet applet, Vector pos, float size) {
        super(applet, pos, size);
    }

    public GeneratorTile(Applet applet, float x, float y, float size) {
        super(applet, x, y, size);
    }
}
