package GeneRLs.geom;

import GeneRLs.geom.Board;
import GeneRLs.geom.Tiles.CrownTile;
import GeneRLs.geom.Tiles.Tile;
import GeneRLs.core.Applet;

public class Game {

    private int totalBlueArmies;
    private int totalRedArmies;
    private int turnNumber;
    private Applet applet;
    private int tileSize;

    private boolean gameOver;
    private Tile.State currentPlayer;

    Board board;
    storage.IntVector BlueQueen;
    storage.IntVector RedQueen;

    public Game(Applet applet) {
        this.applet = applet;
        this.tileSize = 100;
        board = new Board(applet, 8, 8, tileSize);

        totalBlueArmies = 0;
        totalRedArmies = 0;
        turnNumber = 0;
        currentPlayer = Tile.State.BLUE; // or whichever side goes first
        gameOver = false;

        initSpecialTiles();
    }

    private void initSpecialTiles() {
        // Place special tiles like CrownTile, RookTile

        BlueQueen = new storage.IntVector(3,3);
        RedQueen = new storage.IntVector(4,4);

        var pos = board.getTile(BlueQueen).getPosition();
        board.setTile(BlueQueen,new CrownTile(applet,pos,this.tileSize));
        board.getTile(BlueQueen).setState(Tile.State.BLUE);

        pos = board.getTile(RedQueen).getPosition();
        board.setTile(RedQueen,new CrownTile(applet,pos,this.tileSize));
        board.getTile(RedQueen).setState(Tile.State.RED);
    }

    public void nextTurn() {
        turnNumber++;
        currentPlayer = (currentPlayer == Tile.State.BLUE) ? Tile.State.RED : Tile.State.BLUE;
        // Update armies, check victory conditions, etc.
    }

    public void addArmies(Tile.State player, int armies) {
        if (player == Tile.State.BLUE) totalBlueArmies += armies;
        else if (player == Tile.State.RED) totalRedArmies += armies;
    }

    public Tile.State getCurrentPlayer() {
        return currentPlayer;
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
        board.draw();
        // Draw HUD or other overlays here, if necessary
    }
}
