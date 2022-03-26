package com.example.checkers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static com.example.checkers.CheckersApplication.TILE_SIZE;

public class Piece extends StackPane {

    private PieceType type;

    public PieceType getType() {
        return type;
    }

    public Piece(PieceType type, int x, int y) {
        this.type = type;

        relocate(x * TILE_SIZE, y * TILE_SIZE);

        //background ellipse
        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125,TILE_SIZE * 0.26);
        bg.setFill(Color.BLACK);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * 0.03);

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) /2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) /2 + TILE_SIZE * 0.07);

        //body ellipse
        Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125,TILE_SIZE * 0.26);
        ellipse.setFill(type == PieceType.BLACK ? Color.valueOf("#5a5a5a") : Color.valueOf("#a1a1a1"));
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(TILE_SIZE * 0.03);

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) /2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) /2);

        getChildren().addAll(bg, ellipse);
    }
}
