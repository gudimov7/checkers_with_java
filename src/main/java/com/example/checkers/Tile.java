package com.example.checkers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Piece piece;

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean hasPiece() {
        return piece != null;
    }
    
    public Tile(boolean light,int x, int y) {
        setWidth(CheckersApplication.TILE_SIZE);
        setHeight(CheckersApplication.TILE_SIZE);

        relocate(x * CheckersApplication.TILE_SIZE, y * CheckersApplication.TILE_SIZE);
        setFill(light ? Color.valueOf("#f1f1f1") : Color.valueOf("#5f5f5f"));
    }
}
