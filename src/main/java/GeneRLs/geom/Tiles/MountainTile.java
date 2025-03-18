package GeneRLs.geom.Tiles;

import GeneRLs.core.Applet;
import GeneRLs.storage.Color;
import GeneRLs.storage.ColorType;
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

    @Override
    public void drawSprite() {
        Color color = new Color(255,100,100,100); // unclaimed

        sprite.setStroke(applet.color(color));
        sprite.setFill(applet.color(color));
        applet.shape(sprite, x, y +  size/10f, size*0.666f, sprite.height/sprite.width * size*0.666f);
    }
}
