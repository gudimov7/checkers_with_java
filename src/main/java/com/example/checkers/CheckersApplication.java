package com.example.checkers;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckersApplication extends Application {
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tilesGroup = new Group();
    private Group piecesGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tilesGroup, piecesGroup);

        //create board of tiles
        for (int y = 0; y < HEIGHT; y ++) {
            for (int x = 0; x < WIDTH; x ++) {
                Tile tile = new Tile((x +y) %2 == 0, x, y);
                board[x][y] = tile;
                tilesGroup.getChildren().add(tile);

                //populate pieces
                Piece piece = null;
                //populate black
                if (y <= 2 && (x + y) %2 != 0) {
                    piece = makePiece(PieceType.BLACK, x, y);
                }
                //populate white
                if (y >= 5 && (x + y) %2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }
                if (piece != null) {
                    tile.setPiece(piece);
                    piecesGroup.getChildren().add(piece);
                }
            }
        }

        return root;
    }

    private Piece makePiece(PieceType type, int x , int y) {
        Piece piece = new Piece(type, x, y);

        return piece;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Checkers!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}