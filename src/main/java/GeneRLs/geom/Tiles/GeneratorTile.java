package GeneRLs.geom.Tiles;

import GeneRLs.core.Applet;
import GeneRLs.storage.Color;
import GeneRLs.storage.ColorType;
import GeneRLs.storage.Vector;

public class GeneratorTile extends Tile {
    public GeneratorTile(Applet applet, Vector pos, float size, String spriteFilePath) {
        this(applet, pos.x, pos.y, size, spriteFilePath);
    }

    public GeneratorTile(Applet applet, float x, float y, float size, String spriteFilePath) {
        super(applet, x, y, size);
        String commonPath = applet.sketchPath("src/main/java/GeneRLs/data/");
        this.sprite = applet.loadShape(commonPath + spriteFilePath);
    }

    @Override
    public void drawSprite() {
        Color color = switch (state) {
            case UNCLAIMED -> new Color(255,0,65,90);
            case BLUE -> new Color(ColorType.BLUE);
            case RED -> new Color(ColorType.RED);
        };

        //applet.println(applet.frameCount);
        color.setBrightness(100);
        color.setSaturation(25);
        color.setAlpha(50);
        sprite.setStroke(applet.color(color));
        color.setAlpha(70);
        sprite.setFill(applet.color(color));
        applet.shape(sprite, x -  size/18f, y +  size/10f, size*0.666f, sprite.height/sprite.width * size*0.666f);
    }
}
