package GeneRLs.geom;

import GeneRLs.core.Applet;
import GeneRLs.geom.Tiles.CrownTile;
import GeneRLs.geom.Tiles.Tile;
import processing.core.PApplet;
import processing.core.PImage;

public class Board {
    final int rows;
    final int cols;
    private final int tileSize;
    private final Tile[][] tiles;
    private final Applet applet;

    public Board(Applet applet, int rows, int cols, int tileSize) {
        this.applet = applet;
        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;
        tiles = new Tile[rows][cols];

        initBoard();
    }

    private void initBoard() {
        float modifiedTileSize = tileSize;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j] = new Tile(applet, ((float) cols /2 - j - 0.5f)*modifiedTileSize, ((float) rows /2 - i - 0.5f)*modifiedTileSize, tileSize);
            }
        }
    }

    public void setTile(int row, int col, Tile tile) {
        tiles[row][col] = tile;
    }

    public void draw() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.draw();
            }
        }
    }

    public Tile getTile(int r, int c) {
        return tiles[r][c];
    }

    public Tile getTile(storage.IntVector pos) {
        return getTile((int) pos.x, (int) pos.y);
    }

    public void setTile(storage.IntVector pos, Tile tile) {
        setTile((int) pos.x, (int) pos.y, tile);
    }
}
