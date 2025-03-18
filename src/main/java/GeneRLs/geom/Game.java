package GeneRLs.geom;

import GeneRLs.geom.Board;
import GeneRLs.geom.Tiles.CrownTile;
import GeneRLs.geom.Tiles.GeneratorTile;
import GeneRLs.geom.Tiles.MountainTile;
import GeneRLs.geom.Tiles.Tile;
import GeneRLs.core.Applet;
import GeneRLs.storage.ColorType;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static GeneRLs.Main.myFont;
import static processing.core.PApplet.*;

public class Game {

    private int totalBlueArmies;
    private int totalRedArmies;
    private int totalBlueLand;
    private int totalRedLand;
    private int turnNumber;
    private Applet applet;
    private int tileSize;

    private boolean gameOver;

    Board board;
    storage.IntVector BlueQueenPos;
    storage.IntVector RedQueenPos;
    CrownTile blueQueen;
    CrownTile redQueen;
    List<GeneratorTile> redGeneratorTiles;
    List<GeneratorTile> blueGeneratorTiles;
    int rows;
    int cols;

    public Tile selectedTile;
    public storage.IntVector selectedTilePos;

    public Game(Applet applet) {
        this.applet = applet;
        this.tileSize = 100;
        this.rows = 8;
        this.cols = 8;

        board = new Board(applet, rows, cols, tileSize);

        totalBlueArmies = 0;
        totalRedArmies = 0;
        totalBlueLand = 1;
        totalRedLand = 1;

        turnNumber = 0;
        gameOver = false;

        initSpecialTiles();

        redGeneratorTiles = new ArrayList<>();
        blueGeneratorTiles = new ArrayList<>();

        for (int i = 0; i < rows; i++) { // add generatorTiles to list
            for (int j = 0; j < cols; j++) {
                var curTile = board.getTile(i, j);
                if (curTile instanceof GeneratorTile) {
                    if (curTile.getState() == Tile.State.RED)
                        redGeneratorTiles.add((GeneratorTile) curTile);
                    else if (curTile.getState() == Tile.State.BLUE)
                        blueGeneratorTiles.add((GeneratorTile) curTile);
                }
            }
        }

        selectTile(redQueen);
    }

    private void initSpecialTiles() {
        // Place special tiles like CrownTile, RookTile
        // TODO: make easier way to elevate Tiles

        BlueQueenPos = new storage.IntVector(3,3);
        RedQueenPos = new storage.IntVector(4,4);

        var pos = board.getTile(BlueQueenPos).getPosition();
        board.setTile(BlueQueenPos,new CrownTile(applet,pos,this.tileSize));
        board.getTile(BlueQueenPos).setState(Tile.State.BLUE);
        blueQueen = (CrownTile) board.getTile(BlueQueenPos);

        pos = board.getTile(RedQueenPos).getPosition();
        board.setTile(RedQueenPos,new CrownTile(applet,pos,this.tileSize));
        board.getTile(RedQueenPos).setState(Tile.State.RED);
        redQueen = (CrownTile) board.getTile(BlueQueenPos);

        board.setTile(3,4,new MountainTile(applet,board.getTile(3,4).getPosition(),this.tileSize));
        board.setTile(4,3,new MountainTile(applet,board.getTile(4,3).getPosition(),this.tileSize));

    }

    public void nextTurn() {
        turnNumber++;
        // Update armies, check victory conditions, etc.
    }

    public void addArmies(Tile.State player, int armies) {
        if (player == Tile.State.BLUE) totalBlueArmies += armies;
        else if (player == Tile.State.RED) totalRedArmies += armies;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean status) {
        gameOver = status;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public Board getBoard() {
        return board;
    }

    public void draw() {
        update();
        board.draw();
        info();
        highlightSelectedTile();
    }

    long lastUpdatedTime = 90;

    public void highlightSelectedTile(){
        Tile selected = getSeletedTile();
        if (selected == null) {
            return;
        }

        float pulse = 100 + 70 * pow(PApplet.sin((max(0,applet.frameCount - lastUpdatedTime)) * 0.1f),1); // Oscillates between 50 and 150

        applet.noFill();
        applet.stroke(200, 255, 255, pulse); // Yellow with pulsating alpha
        float x = selected.getPosition().x;
        float y = selected.getPosition().y;
        float size = tileSize;
        applet.strokeWeight(6);
        applet.rect(
                x - size/2f, y - size/2f,
                x + size/2f, y + size/2f
        );
    }

    public void update() { // TODO: definitely needs some work
        int frameUpdate = 30;

        turnNumber = applet.frameCount/frameUpdate;

        if (applet.frameCount % frameUpdate == 0) {
            for (var redTile : redGeneratorTiles) {
                redTile.army++;
            }
            for (var blueTile : blueGeneratorTiles) {
                blueTile.army++;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (applet.frameCount % (frameUpdate*25) == 0) {
                    if (board.getTile(i,j).getState() != Tile.State.UNCLAIMED) {
                        board.getTile(i,j).army++;
                    }
                }
            }
        }

    }

    private void info(){
        applet.textFont(myFont);
        applet.textSize(80);
        applet.fill(ColorType.ORANGE);
        applet.textAlign(PApplet.LEFT, PApplet.CENTER);
        applet.text("Turn: " + turnNumber, -tileSize * board.cols/2, tileSize * board.rows/2 + 50);
    }

    public void selectTile(Tile tile) {
        selectedTile = tile;
        selectedTilePos = tile.getBoardPosition();
    }

    public Tile getSeletedTile() {
        return selectedTile;
    }

    public void captureTile(Tile attacker, Tile defender) {

    }

    public boolean inRange(storage.IntVector pos) { // TODO: make sure rows and cols not mixed up
        return pos.x >= 0 && pos.x < this.board.rows && pos.y >= 0 && pos.y < this.board.cols;
    }
}
